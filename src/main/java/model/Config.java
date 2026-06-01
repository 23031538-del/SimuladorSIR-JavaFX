package model;
/**
 * Constantes de configuración global para la simulación.
 * Puedes cargar estos valores desde properties si prefieres configurarlos en tiempo de ejecución.
 */
public final class Config {
    private Config() {
    }

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int FPS = 60;

    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 700;

    public static final int DEFAULT_N_AGENTS = 300;
    public static final double DEFAULT_BETA = 0.30;
    public static final double DEFAULT_GAMMA = 0.08;
    public static final double DEFAULT_INFECTION_RADIUS = 1.5;
    public static final String DEFAULT_MODE = "contacto";

    public static final double AGENT_RADIUS = 2.0;
}
