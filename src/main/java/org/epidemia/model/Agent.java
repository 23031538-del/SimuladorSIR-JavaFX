package org.epidemia.model;

public class Agent {
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private String state; // "S", "I", "R"
    private double timeInfected;

    public Agent(double posX, double posY, double velX, double velY, String state) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.state = state;
        this.timeInfected = 0.0;
    }

    public void move(double dt, double worldWidth, double worldHeight) {
        posX += velX * dt;
        posY += velY * dt;

        if (posX < 0 || posX > worldWidth) velX *= -1;
        if (posY < 0 || posY > worldHeight) velY *= -1;
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

