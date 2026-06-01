package service;

import model.ModelFactory;
import model.Simulation;
import Observer.SimulationObservable;
import Strategy.SimulationStrategy;
import Strategy.SimpleRandomStrategy;

import java.util.*;
import java.util.concurrent.*;

public class SimulationService {
    private final Map<UUID, Simulation> store = new ConcurrentHashMap<>();
    private final SimulationObservable observable = new SimulationObservable();
    private SimulationStrategy strategy = new SimpleRandomStrategy();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public UUID startSimulation(String name, String paramsJson) {
        Simulation s = ModelFactory.createSimulation(name, paramsJson);
        s.setStatus(model.SimulationStatus.RUNNING);
        store.put(s.getId(), s);
        observable.notifyAllObservers(s);

        // ejecutar en background
        executor.submit(() -> {
            try {
                String result = strategy.run(s);
                s.setResultJson(result);
                s.setStatus(model.SimulationStatus.COMPLETED);
            } catch (Exception e) {
                s.setStatus(model.SimulationStatus.FAILED);
            } finally {
                observable.notifyAllObservers(s);
            }
        });

        return s.getId();
    }

    public Simulation get(UUID id) { return store.get(id); }
    public Collection<Simulation> list() { return store.values(); }
    public void setStrategy(SimulationStrategy strategy) { this.strategy = strategy; }
    public SimulationObservable getObservable() { return observable; }
}