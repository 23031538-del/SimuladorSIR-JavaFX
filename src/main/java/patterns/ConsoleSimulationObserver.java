package patterns;
/**
 * Implementación de ejemplo que imprime las estadísticas en consola.
 * Útil para debugging o para pruebas rápidas.
 */
public class ConsoleSimulationObserver implements SimulationObserver {

    @Override
    public void onSimulationUpdate(SimulationStats stats) {
        System.out.printf("[Sim] t=%.2f  S=%d  I=%d  R=%d  @%s%n",
                stats.getTime(),
                stats.getSusceptibles(),
                stats.getInfectados(),
                stats.getRecuperados(),
                stats.getTimestamp().toString());
    }
}
