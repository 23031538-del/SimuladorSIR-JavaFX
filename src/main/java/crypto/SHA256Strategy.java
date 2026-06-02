package crypto;

import java.security.MessageDigest;

public class SHA256Strategy
        implements CifradoStrategy {

    @Override
    public String cifrar(String texto) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    digest.digest(
                            texto.getBytes("UTF-8")
                    );

            StringBuilder resultado =
                    new StringBuilder();

            for (byte b : hash) {

                resultado.append(
                        String.format("%02x", b)
                );
            }

            return resultado.toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al generar hash SHA-256"
            );
        }
    }
}
