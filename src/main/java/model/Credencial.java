package model;

/**
 * Entidad que representa una credencial asociada a un usuario.
 */
public class Credencial {
    private int idCredencial;
    private int idUsuario;
    private String sitio;
    private String correo;
    private String passwordCifrado;
    private String notas;

    public Credencial() {
    }

    public Credencial(int idCredencial, int idUsuario, String sitio, String correo, String passwordCifrado, String notas) {
        this.idCredencial = idCredencial;
        this.idUsuario = idUsuario;
        this.sitio = sitio;
        this.correo = correo;
        this.passwordCifrado = passwordCifrado;
        this.notas = notas;
    }

    public int getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(int idCredencial) {
        this.idCredencial = idCredencial;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordCifrado() {
        return passwordCifrado;
    }

    public void setPasswordCifrado(String passwordCifrado) {
        this.passwordCifrado = passwordCifrado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "idCredencial=" + idCredencial +
                ", idUsuario=" + idUsuario +
                ", sitio='" + sitio + '\'' +
                ", correo='" + correo + '\'' +
                ", passwordCifrado='" + passwordCifrado + '\'' +
                ", notas='" + notas + '\'' +
                '}';
    }
}
