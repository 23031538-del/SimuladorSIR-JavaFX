package model;

public class Agent {

    private double x;
    private double y;

    private double velocidadX;
    private double velocidadY;

    private Estado estado;

    private double tiempoInfectado;

    public Agent(
            double x,
            double y,
            double velocidadX,
            double velocidadY,
            Estado estado) {

        this.x = x;
        this.y = y;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.estado = estado;
        this.tiempoInfectado = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTiempoInfectado() {
        return tiempoInfectado;
    }

    public void setTiempoInfectado(double tiempoInfectado) {
        this.tiempoInfectado = tiempoInfectado;
    }
}