package crypto;

public class CryptoFactory {

    public static CifradoStrategy getStrategy(
            String tipo) {

        switch (tipo.toUpperCase()) {

            case "SHA256":
                return new SHA256Strategy();

            default:
                throw new IllegalArgumentException(
                        "Tipo de cifrado no soportado."
                );
        }
    }
}
