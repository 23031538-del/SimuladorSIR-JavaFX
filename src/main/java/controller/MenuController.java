package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Usuario;

public class MenuController {

    private Usuario usuario;

    @FXML
    private Button btnSimulacion;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    @FXML
    public void abrirSimulacion() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/configuracion.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) btnSimulacion
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnSimulacion.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirEstadisticas() {

        try {

            System.out.println("Abriendo estadísticas");

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/estadisticas.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) btnSimulacion
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            System.out.println("Estadísticas abiertas");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
