package model;

public class Resultado {

    private int tiempo;

    private int susceptibles;

    private int infectados;

    private int recuperados;

    public Resultado(int tiempo,
                     int susceptibles,
                     int infectados,
                     int recuperados) {

        this.tiempo = tiempo;
        this.susceptibles = susceptibles;
        this.infectados = infectados;
        this.recuperados = recuperados;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getSusceptibles() {
        return susceptibles;
    }

    public void setSusceptibles(int susceptibles) {
        this.susceptibles = susceptibles;
    }

    public int getInfectados() {
        return infectados;
    }

    public void setInfectados(int infectados) {
        this.infectados = infectados;
    }

    public int getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(int recuperados) {
        this.recuperados = recuperados;
    }
}
