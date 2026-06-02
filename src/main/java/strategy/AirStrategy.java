package strategy;

import model.Agent;
import model.Estado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirStrategy implements InfectionStrategy {

    private Random random = new Random();

    @Override
    public void infectar(
            List<Agent> agentes,
            double beta,
            double radioInfeccion,
            double deltaTiempo) {

        List<Agent> nuevosInfectados = new ArrayList<>();

        for (Agent agente : agentes) {

            if (agente.getEstado() == Estado.SUSCEPTIBLE) {

                if (random.nextDouble()
                        < beta * deltaTiempo) {

                    nuevosInfectados.add(agente);
                }
            }
        }

        for (Agent agente : nuevosInfectados) {
            agente.setEstado(Estado.INFECTADO);
        }
    }
}