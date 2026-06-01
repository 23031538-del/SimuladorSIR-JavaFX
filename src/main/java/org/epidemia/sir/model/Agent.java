package org.epidemia.model;

public class Agent {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private String state; // "S", "I", "R"
    private double timeInfected;

    public Agent(double x, double y, double vx, double vy, String state) {
        this.posx = x;
        this.posy = y;
        this.vx = vx;
        this.vy = vy;
        this.state = state;
        this.timeInfected = 0.0;
    }

    public void move(double dt, double worldWidth, double worldHeight) {
        x += vx * dt;
        y += vy * dt;

        if (x < 0 ) {
            x = 0;
            vx *= -1;
        } else if (x > worldWidth) {
            x = worldWidth;
        }
        if (y < 0 ) {
            y = 0;
            vy *= -1;
    }else if( y > worldHeight ) {

    }

    public void update(double dt, double gamma) {
        if ("I".equals(state)) {
            timeInfected += dt;
            if (Math.random() < gamma * dt) {
                state = "R";
            }
        }
    }

    // Getters y setters
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
}

