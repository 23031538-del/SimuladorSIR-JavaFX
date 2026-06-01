package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un agente en la simulación SIR.
 */
public class Agent {
    private static final AtomicInteger ID_GEN = new AtomicInteger(1);

    private final int id;
    private double x;
    private double z;
    private double vx;
    private double vz;
    private State state;
    private double timeInfected;

    public enum State {S, I, R}

    public Agent(double x, double z, double vx, double vz, State state) {
        this.id = ID_GEN.getAndIncrement();
        this.x = x;
        this.z = z;
        this.vx = vx;
        this.vz = vz;
        this.state = state;
        this.timeInfected = 0.0;
    }

    // Movimiento
    public void updatePosition(double dt) {
        this.x += vx * dt;
        this.z += vz * dt;
    }

    // Invertir velocidad (colisión)
    public void invertVelocity() {
        this.vx = -this.vx;
        this.vz = -this.vz;
    }

    // Infectar
    public void infect() {
        if (this.state == State.S) {
            this.state = State.I;
            this.timeInfected = 0.0;
        }
    }

    // Actualizar tiempo infectado y posible recuperación
    public boolean updateInfection(double dt, double gamma) {
        if (this.state == State.I) {
            this.timeInfected += dt;
            if (Math.random() < gamma * dt) {
                this.state = State.R;
                return true;
            }
        }
        return false;
    }

    // Getters / Setters
    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getTimeInfected() {
        return timeInfected;
    }

    public void setTimeInfected(double timeInfected) {
        this.timeInfected = timeInfected;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", x=" + x +
                ", z=" + z +
                ", vx=" + vx +
                ", vz=" + vz +
                ", state=" + state +
                ", timeInfected=" + timeInfected +
                '}';
    }
}
