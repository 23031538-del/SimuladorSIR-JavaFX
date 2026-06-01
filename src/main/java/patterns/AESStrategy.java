package patterns;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * Estrategia AES para cifrado simétrico.
 * Devuelve el resultado en Base64.
 *
 * Nota: esta implementación deriva una clave AES de 16 bytes a partir
 * de la cadena proporcionada usando SHA-256 y tomando los primeros 16 bytes.
 * Para producción considera usar un Key Derivation Function (PBKDF2) con salt.
 */
public class AESStrategy implements CifradoStrategy {

    private static final String ALGORITHM = "AES";

    @Override
    public String cifrar(String texto, String clave) {
        try {
            byte[] keyBytes = deriveKeyBytes(clave);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encrypted = cipher.doFinal(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error cifrando con AES", e);
        }
    }

    /**
     * Deriva una clave de 16 bytes a partir de la cadena proporcionada.
     * Toma SHA-256 y usa los primeros 16 bytes.
     */
    private byte[] deriveKeyBytes(String clave) throws Exception {
        if (clave == null) {
            throw new IllegalArgumentException("La clave AES no puede ser null");
        }
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = sha.digest(clave.getBytes("UTF-8"));
        return Arrays.copyOf(key, 16); // AES-128
    }
}
