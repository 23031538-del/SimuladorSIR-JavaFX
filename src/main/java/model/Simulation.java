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

    private InfectionStrategy strategy;

    private int tiempo = 0;

    private static final double ANCHO = 800;
    private static final double ALTO = 600;

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

    public void setStrategy(InfectionStrategy strategy) {
        this.strategy = strategy;
    }

    public InfectionStrategy getStrategy() {
        return strategy;
    }

    public void step() {

        moverAgentes();

        if (strategy != null) {

            strategy.infectar(
                    agentes,
                    configuracion.getBeta(),
                    configuracion.getRadioInfeccion(),
                    1.0
            );
        }

        recuperarAgentes();

        tiempo++;

        if (tiempo % 60 == 0) {

            guardarResultado(tiempo / 60);
        }

        notificarObservers();
    }

    private void moverAgentes() {

        for (Agent agente : agentes) {

            agente.setX(
                    agente.getX()
                            + agente.getVelocidadX()
            );

            agente.setY(
                    agente.getY()
                            + agente.getVelocidadY()
            );

            if (agente.getX() <= 0 ||
                    agente.getX() >= ANCHO) {

                agente.setVelocidadX(
                        -agente.getVelocidadX()
                );
            }

            if (agente.getY() <= 0 ||
                    agente.getY() >= ALTO) {

                agente.setVelocidadY(
                        -agente.getVelocidadY()
                );
            }
        }
    }

    private void recuperarAgentes() {

        for (Agent agente : agentes) {

            if (agente.getEstado() == Estado.INFECTADO) {

                agente.setTiempoInfectado(
                        agente.getTiempoInfectado() + 1
                );

                if (agente.getTiempoInfectado() >= 300) {

                    agente.setEstado(
                            Estado.RECUPERADO
                    );
                }
            }
        }
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

        historial.add(
                new Resultado(
                        tiempo,
                        contarSusceptibles(),
                        contarInfectados(),
                        contarRecuperados()
                )
        );
    }

    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {

        for (Observer observer : observers) {

            observer.actualizar();
        }
    }
}
