package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Motor de la simulación SIR. Mantiene la lista de agentes y edificios,
 * y aplica la lógica de movimiento, colisiones, contagio y recuperación.
 */
public class Simulation {
    private final List<Agent> agents = new ArrayList<>();
    private final List<Building> buildings = new ArrayList<>();
    private final Random random = new Random();

    private double infectionRadius;
    private String mode; // "contacto" o "aire"
    private double beta;
    private double gamma;
    private double time = 0.0;

    public Simulation(int nAgents, double infectionRadius, String mode, double beta, double gamma) {
        this.infectionRadius = infectionRadius;
        this.mode = mode;
        this.beta = beta;
        this.gamma = gamma;
        createAgents(nAgents);
        createBuildings();
    }

    public void createAgents(int nAgents) {
        agents.clear();
        for (int i = 0; i < nAgents; i++) {
            double x = randomUniform(-Config.WORLD_WIDTH / 2.0, Config.WORLD_WIDTH / 2.0);
            double z = randomUniform(-Config.WORLD_HEIGHT / 2.0, Config.WORLD_HEIGHT / 2.0);
            double vx = randomUniform(-6.0, 6.0);
            double vz = randomUniform(-6.0, 6.0);
            agents.add(new Agent(x, z, vx, vz, Agent.State.S));
        }
        if (!agents.isEmpty()) {
            agents.get(0).setState(Agent.State.I); // semilla inicial
        }
    }

    public void createBuildings() {
        buildings.clear();
        buildings.add(new Building(-5, 5, 6, 10));
        buildings.add(new Building(8, -8, 5, 12));
        buildings.add(new Building(0, 12, 7, 15));
    }

    public void step(double dt) {
        // Movimiento y colisiones con límites y edificios
        for (Agent a : agents) {
            a.updatePosition(dt);

            // Límites del mundo (rebote)
            double halfW = Config.WORLD_WIDTH / 2.0;
            double halfH = Config.WORLD_HEIGHT / 2.0;
            if (a.getX() < -halfW || a.getX() > halfW) a.setVx(-a.getVx());
            if (a.getZ() < -halfH || a.getZ() > halfH) a.setVz(-a.getVz());

            // Colisión con edificios (simple comprobación AABB)
            for (Building b : buildings) {
                if (Math.abs(a.getX() - b.getX()) < b.getWidth() / 2.0 &&
                        Math.abs(a.getZ() - b.getZ()) < b.getWidth() / 2.0) {
                    a.invertVelocity();
                }
            }
        }

        // Contagio
        double r2 = infectionRadius * infectionRadius;
        for (int i = 0; i < agents.size(); i++) {
            Agent a = agents.get(i);
            if (a.getState() != Agent.State.I) continue;
            for (int j = 0; j < agents.size(); j++) {
                if (i == j) continue;
                Agent b = agents.get(j);
                if (b.getState() != Agent.State.S) continue;

                if ("contacto".equalsIgnoreCase(mode)) {
                    double dx = a.getX() - b.getX();
                    double dz = a.getZ() - b.getZ();
                    double dist2 = dx * dx + dz * dz;
                    if (dist2 < r2 && random.nextDouble() < beta * dt) {
                        b.infect();
                    }
                } else if ("aire".equalsIgnoreCase(mode)) {
                    if (random.nextDouble() < beta * dt) {
                        b.infect();
                    }
                }
            }
        }

        // Recuperación
        for (Agent a : agents) {
            a.updateInfection(dt, gamma);
        }

        time += dt;
    }

    private double randomUniform(double a, double b) {
        return a + random.nextDouble() * (b - a);
    }

    // Contadores S/I/R
    public int countSusceptible() {
        return (int) agents.stream().filter(x -> x.getState() == Agent.State.S).count();
    }

    public int countInfected() {
        return (int) agents.stream().filter(x -> x.getState() == Agent.State.I).count();
    }

    public int countRecovered() {
        return (int) agents.stream().filter(x -> x.getState() == Agent.State.R).count();
    }

    // Getters / setters
    public List<Agent> getAgents() {
        return agents;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public double getInfectionRadius() {
        return infectionRadius;
    }

    public void setInfectionRadius(double infectionRadius) {
        this.infectionRadius = infectionRadius;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getTime() {
        return time;
    }
}
