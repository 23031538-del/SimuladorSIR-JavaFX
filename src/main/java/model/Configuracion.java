package model;

public class Configuracion {

    private int numeroAgentes;

    private double beta;

    private double gamma;

    private double radioInfeccion;

    private double velocidad;

    public Configuracion(int numeroAgentes,
                         double beta,
                         double gamma,
                         double radioInfeccion,
                         double velocidad) {

        this.numeroAgentes = numeroAgentes;
        this.beta = beta;
        this.gamma = gamma;
        this.radioInfeccion = radioInfeccion;
        this.velocidad = velocidad;
    }

    public int getNumeroAgentes() {
        return numeroAgentes;
    }

    public void setNumeroAgentes(int numeroAgentes) {
        this.numeroAgentes = numeroAgentes;
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

    public double getRadioInfeccion() {
        return radioInfeccion;
    }

    public void setRadioInfeccion(double radioInfeccion) {
        this.radioInfeccion = radioInfeccion;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
}
