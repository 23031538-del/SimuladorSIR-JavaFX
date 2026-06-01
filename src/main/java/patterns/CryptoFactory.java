package patterns;

/**
 * Fábrica simple para obtener instancias de CifradoStrategy.
 * Implementa el patrón Factory Method.
 */
public final class CryptoFactory {

    private CryptoFactory() { /* no instanciable */ }

    /**
     * Devuelve una estrategia de cifrado según el nombre.
     *
     * @param tipo "AES" o "SHA256" (case-insensitive)
     * @return instancia de CifradoStrategy
     * @throws IllegalArgumentException si el tipo no está soportado
     */
    public static CifradoStrategy getStrategy(String tipo) {
        if (tipo == null) throw new IllegalArgumentException("Tipo no puede ser null");
        switch (tipo.trim().toUpperCase()) {
            case "AES":
                return new AESStrategy();
            case "SHA256":
            case "SHA-256":
                return new SHA256Strategy();
            default:
                throw new IllegalArgumentException("Tipo de cifrado no soportado: " + tipo);
        }
    }
}
