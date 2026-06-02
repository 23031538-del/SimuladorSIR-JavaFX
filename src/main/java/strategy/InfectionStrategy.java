package strategy;

import model.Agent;
import java.util.List;

public interface InfectionStrategy {

    void infectar(
            List<Agent> agentes,
            double beta,
            double radioInfeccion,
            double deltaTiempo
    );

}