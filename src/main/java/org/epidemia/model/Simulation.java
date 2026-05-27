package org.epidemia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private List<Agent> agents;
    private List<Hospital> hospitals;
    private double time;
    private List<int[]> history; // [S, I, R]

    private static final int N_AGENTS = 300;
    private static final double WORLD_WIDTH = 1000;
    private static final double WORLD_HEIGHT = 700;
    private static final double BETA = 0.35;
    private static final double GAMMA = 0.08;
    private static final double INFECTION_RADIUS = 12;

    public Simulation() {
        agents = new ArrayList<>();
        hospitals = new ArrayList<>();
        history = new ArrayList<>();
        time = 0;
        createAgents();
        createHospitals();
    }

    private void createAgents() {
        Random rand = new Random();
        agents.clear();
        for (int i = 0; i < N_AGENTS; i++) {
            double x = rand.nextDouble() * WORLD_WIDTH;
            double y = rand.nextDouble() * WORLD_HEIGHT;
            double vx = rand.nextDouble() * 120 - 60;
            double vy = rand.nextDouble() * 120 - 60;
            agents.add(new Agent(x, y, vx, vy, "S"));
        }
        agents.get(0).setState("I"); // paciente cero
    }

    private void createHospitals() {
        hospitals.clear();
        hospitals.add(new Hospital(250, 300, 40));
        hospitals.add(new Hospital(700, 500, 40));
    }

    public int[] step(double dt) {
        // Movimiento
        for (Agent a : agents) {
            a.move(dt, WORLD_WIDTH, WORLD_HEIGHT);
        }

        // Contagios
        double r2 = INFECTION_RADIUS * INFECTION_RADIUS;
        for (int i = 0; i < agents.size(); i++) {
            Agent a = agents.get(i);
            if (!"I".equals(a.getState())) continue;

            for (int j = i + 1; j < agents.size(); j++) {
                Agent b = agents.get(j);
                if (!"S".equals(b.getState())) continue;

                double dx = a.getPosX() - b.getPosX();
                double dy = a.getPosY() - b.getPosY();
                double dist2 = dx * dx + dy * dy;

                if (dist2 < r2 && Math.random() < BETA * dt) {
                    b.setState("I");
                }
            }
        }

        // Hospitales
        for (Agent a : agents) {
            if (!"I".equals(a.getState())) continue;
            for (Hospital h : hospitals) {
                double dx = a.getPosX() - h.getX();
                double dy = a.getPosY() - h.getY();
                double dist2 = dx * dx + dy * dy;
                if (dist2 < h.getRadius() * h.getRadius() && Math.random() < 0.4 * dt) {
                    a.setState("R");
                }
            }
        }

        // Recuperación
        for (Agent a : agents) {
            a.update(dt, GAMMA);
        }

        // Historial
        int[] counts = countStates();
        history.add(counts);
        time += dt;
        return counts;
    }

    private int[] countStates() {
        int s = 0, i = 0, r = 0;
        for (Agent a : agents) {
            switch (a.getState()) {
                case "S": s++; break;
                case "I": i++; break;
                case "R": r++; break;
            }
        }
        return new int[]{s, i, r};
    }

    public List<Agent> getAgents() { return agents; }
    public List<Hospital> getHospitals() { return hospitals; }
    public List<int[]> getHistory() { return history; }
}

