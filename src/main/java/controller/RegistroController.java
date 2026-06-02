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

public class RegistroController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmarField;

    @FXML
    private Label mensajeLabel;

    private UsuarioDAO usuarioDAO;

    public RegistroController() {

        try {

            usuarioDAO =
                    new UsuarioDAOImpl();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void registrar() {

        try {

            String username =
                    usernameField.getText();

            String password =
                    passwordField.getText();

            String confirmar =
                    confirmarField.getText();

            if (!password.equals(confirmar)) {

                mensajeLabel.setText(
                        "Las contraseñas no coinciden"
                );

                return;
            }

            CifradoStrategy sha =
                    CryptoFactory.getStrategy(
                            "SHA256"
                    );

            String hash =
                    sha.cifrar(password);

            Usuario usuario =
                    new Usuario(
                            0,
                            username,
                            hash
                    );

            usuarioDAO.insertar(usuario);

            mensajeLabel.setText(
                    "Usuario registrado"
            );

        } catch (Exception e) {

            mensajeLabel.setText(
                    "Error al registrar"
            );
        }
    }

    @FXML
    private void volverLogin() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/login.fxml"
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