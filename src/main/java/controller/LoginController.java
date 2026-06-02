package controller;

import crypto.CifradoStrategy;
import crypto.CryptoFactory;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label mensajeLabel;

    private UsuarioDAO usuarioDAO;

    public LoginController() {

        try {

            usuarioDAO = new UsuarioDAOImpl();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void login() {

        try {

            String username =
                    usernameField.getText();

            String password =
                    passwordField.getText();

            Usuario usuario =
                    usuarioDAO.buscarPorUsername(
                            username
                    );

            if (usuario == null) {

                mensajeLabel.setText(
                        "Usuario no encontrado"
                );

                return;
            }

            CifradoStrategy sha =
                    CryptoFactory.getStrategy(
                            "SHA256"
                    );

            String hash =
                    sha.cifrar(password);

            if (hash.equals(
                    usuario.getPasswordHash()
            )) {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource(
                                        "/view/menu.fxml"
                                )
                        );

                Scene scene =
                        new Scene(loader.load());

                MenuController controller =
                        loader.getController();

                controller.setUsuario(usuario);

                Stage stage =
                        (Stage) usernameField
                                .getScene()
                                .getWindow();

                stage.setScene(scene);

            } else {

                mensajeLabel.setText(
                        "Contraseña incorrecta"
                );
            }

        } catch (Exception e) {

            mensajeLabel.setText(
                    "Error de conexión"
            );
        }
    }

    @FXML
    private void abrirRegistro() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/registro.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) usernameField
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}