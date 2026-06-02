package strategy;

import model.Agent;
import model.Estado;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactStrategy implements InfectionStrategy {
    private Random random = new Random();

    @Override
    public void infectar(
            List<Agent> agentes,
            double beta,
            double radioInfeccion,
            double deltaTiempo) {

        List<Agent> nuevosInfectados = new ArrayList<>();
        double radio2 = radioInfeccion * radioInfeccion;
        for (Agent infectado : agentes) {
            if (infectado.getEstado() != Estado.INFECTADO) {
                continue;
            }
            for (Agent susceptible : agentes)
            {
                if (susceptible.getEstado() != Estado.SUSCEPTIBLE)
                {
                    continue;
                }
                double dx = infectado.getX() - susceptible.getX();
                double dy = infectado.getY() - susceptible.getY();

                double distancia2 = dx * dx + dy * dy;

                if (distancia2 <= radio2) {

                    if (random.nextDouble()
                            < beta * deltaTiempo) {

                        nuevosInfectados.add(susceptible);
                    }
                }
            }
        }

        for (Agent agente : nuevosInfectados) {
            agente.setEstado(Estado.INFECTADO);
        }
    }
}
