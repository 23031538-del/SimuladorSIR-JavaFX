package patterns;

/**
 * Interfaz para estrategias de cifrado / hashing.
 * Implementa el patrón Strategy para permitir intercambiar algoritmos.
 */
public interface CifradoStrategy {
    /**
     * Cifra o hashea el texto proporcionado.
     *
     * @param texto texto plano a cifrar o hashear
     * @param clave clave opcional (puede ser null para algoritmos de hash)
     * @return texto cifrado o hash en formato legible (Base64 o hex)
     */
    String cifrar(String texto, String clave);
}
