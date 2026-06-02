package factory;

import model.Agent;
import model.Estado;

import java.util.Random;

public class AgentFactory {

    private static final Random random =
            new Random();

    public static Agent crearSusceptible(
            int ancho,
            int alto,
            double velocidad) {

        double x =
                random.nextDouble() * ancho;

        double y =
                random.nextDouble() * alto;

        double vx =
                (random.nextDouble() * 2 - 1)
                        * velocidad;

        double vy =
                (random.nextDouble() * 2 - 1)
                        * velocidad;

        return new Agent(
                x,
                y,
                vx,
                vy,
                Estado.SUSCEPTIBLE
        );
    }

    public static Agent crearInfectado(
            int ancho,
            int alto,
            double velocidad) {

        double x =
                random.nextDouble() * ancho;

        double y =
                random.nextDouble() * alto;

        double vx =
                (random.nextDouble() * 2 - 1)
                        * velocidad;

        double vy =
                (random.nextDouble() * 2 - 1)
                        * velocidad;

        return new Agent(
                x,
                y,
                vx,
                vy,
                Estado.INFECTADO
        );
    }
}
