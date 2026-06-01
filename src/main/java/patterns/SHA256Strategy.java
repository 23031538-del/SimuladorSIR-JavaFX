package patterns;

import java.security.MessageDigest;

/**
 * Estrategia que genera un hash SHA-256 en formato hexadecimal.
 * Útil para almacenar/verificar contraseñas (aunque para producción se recomienda PBKDF2/BCrypt).
 */
public class SHA256Strategy implements CifradoStrategy {

    @Override
    public String cifrar(String texto, String claveIgnorada) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando SHA-256", e);
        }
    }
}