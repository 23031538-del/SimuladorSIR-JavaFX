package patterns;

import java.time.Instant;

/**
 * Contenedor inmutable con estadísticas de la simulación que se pasan a los observadores.
 */
public final class SimulationStats {
    private final int susceptibles;
    private final int infectados;
    private final int recuperados;
    private final double time;
    private final Instant timestamp;

    public SimulationStats(int susceptibles, int infectados, int recuperados, double time) {
        this.susceptibles = susceptibles;
        this.infectados = infectados;
        this.recuperados = recuperados;
        this.time = time;
        this.timestamp = Instant.now();
    }

    public int getSusceptibles() { return susceptibles; }
    public int getInfectados() { return infectados; }
    public int getRecuperados() { return recuperados; }
    public double getTime() { return time; }
    public Instant getTimestamp() { return timestamp; }
}
