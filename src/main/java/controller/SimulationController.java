package controller;

import model.Simulation;
import service.SimulationService;

import java.util.Collection;
import java.util.UUID;

public class SimulationController {
    private final SimulationService service = new SimulationService();

    public UUID start(String name, String paramsJson) {
        return service.startSimulation(name, paramsJson);
    }

    public Simulation get(UUID id) { return service.get(id); }
    public Collection<Simulation> list() { return service.list(); }
    public SimulationService getService() { return service; }
}
