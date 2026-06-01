package patterns;

import java.time.Instant;

/**
 * Interfaz Observer para recibir notificaciones de la simulación.
 * Implementa el patrón Observer: la simulación notificará a los observadores
 * cuando cambien los conteos o en cada tick, según la implementación.
 */
public interface SimulationObserver {
    /**
     * Llamado cuando la simulación produce una actualización relevante.
     *
     * @param stats objeto con los conteos y tiempo de la simulación
     */
    void onSimulationUpdate(SimulationStats stats);
}
