package model;

import observer.Observer;
import observer.Subject;
import strategy.InfectionStrategy;

import java.util.ArrayList;
import java.util.List;


public class Simulation implements Subject {

    private List<Agent> agentes;

    private Configuracion configuracion;

    private List<Resultado> historial;

    private List<Observer> observers;

    public Simulation(Configuracion configuracion) {

        this.configuracion = configuracion;

        agentes = new ArrayList<>();

        historial = new ArrayList<>();

        observers = new ArrayList<>();
    }

    public List<Agent> getAgentes() {
        return agentes;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public List<Resultado> getHistorial() {
        return historial;
    }

    public void agregarAgente(Agent agent) {
        agentes.add(agent);
    }

    public int contarSusceptibles() {

        int contador = 0;

        for (Agent a : agentes) {
            if (a.getEstado() == Estado.SUSCEPTIBLE) {
                contador++;
            }
        }

        return contador;
    }

    private InfectionStrategy strategy;

    public void setStrategy(InfectionStrategy strategy) {
        this.strategy = strategy;
    }

    public InfectionStrategy getStrategy() {
        return strategy;
    }


    public int contarInfectados() {

        int contador = 0;

        for (Agent a : agentes) {
            if (a.getEstado() == Estado.INFECTADO) {
                contador++;
            }
        }

        return contador;
    }

    public int contarRecuperados() {

        int contador = 0;

        for (Agent a : agentes) {
            if (a.getEstado() == Estado.RECUPERADO) {
                contador++;
            }
        }

        return contador;
    }

    public void guardarResultado(int tiempo) {

        Resultado resultado = new Resultado(
                tiempo,
                contarSusceptibles(),
                contarInfectados(),
                contarRecuperados()
        );

        historial.add(resultado);
    }
    @Override
    public void agregarObserver(
            Observer observer) {

        observers.add(observer);
    }

    @Override
    public void eliminarObserver(
            Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {

        for (Observer observer : observers) {
            observer.actualizar();
        }
    }

}
