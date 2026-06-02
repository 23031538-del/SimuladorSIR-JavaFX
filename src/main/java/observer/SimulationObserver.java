package observer;

import model.Simulation;

public interface SimulationObserver
        extends Observer {

    void actualizar(
            Simulation simulation
    );
}