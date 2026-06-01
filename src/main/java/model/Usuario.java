package model;

public class Usuario {
    private int idUsuario;
    private String username;
    private String passwordHash; // SHA-256 u otro hash
    private String claveAES;     // clave AES del usuario (Por si las moscas)

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String passwordHash, String claveAES) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.claveAES = claveAES;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getClaveAES() {
        return claveAES;
    }

    public void setClaveAES(String claveAES) {
        this.claveAES = claveAES;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", passwordHash='" + (passwordHash != null ? "[PROTECTED]" : null) + '\'' +
                ", claveAES='" + (claveAES != null ? "[PROTECTED]" : null) + '\'' +
                '}';
    }
}
