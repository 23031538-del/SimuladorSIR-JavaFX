package controller;

import factory.AgentFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Configuracion;
import model.Simulation;
import strategy.AirStrategy;
import strategy.ContactStrategy;

public class ConfiguracionController {

    @FXML
    private TextField txtAgentes;

    @FXML
    private TextField txtBeta;

    @FXML
    private TextField txtGamma;

    @FXML
    private TextField txtRadio;

    @FXML
    private TextField txtVelocidad;

    @FXML
    private ComboBox<String> cmbEstrategia;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        cmbEstrategia.getItems().add("Contacto");
        cmbEstrategia.getItems().add("Aire");

        cmbEstrategia.getSelectionModel().selectFirst();

        txtAgentes.setText("100");
        txtBeta.setText("0.5");
        txtGamma.setText("0.1");
        txtRadio.setText("15");
        txtVelocidad.setText("2");
    }

    @FXML
    public void iniciarSimulacion() {

        try {

            int agentes =
                    Integer.parseInt(
                            txtAgentes.getText()
                    );

            double beta =
                    Double.parseDouble(
                            txtBeta.getText()
                    );

            double gamma =
                    Double.parseDouble(
                            txtGamma.getText()
                    );

            double radio =
                    Double.parseDouble(
                            txtRadio.getText()
                    );

            double velocidad =
                    Double.parseDouble(
                            txtVelocidad.getText()
                    );

            Configuracion config =
                    new Configuracion(
                            agentes,
                            beta,
                            gamma,
                            radio,
                            velocidad
                    );

            Simulation simulation =
                    new Simulation(config);

            for(int i = 0; i < agentes - 1; i++) {

                simulation.agregarAgente(
                        AgentFactory.crearSusceptible(
                                800,
                                600,
                                velocidad
                        )
                );
            }

            simulation.agregarAgente(
                    AgentFactory.crearInfectado(
                            800,
                            600,
                            velocidad
                    )
            );

            if(cmbEstrategia
                    .getValue()
                    .equals("Contacto")) {

                simulation.setStrategy(
                        new ContactStrategy()
                );

            } else {

                simulation.setStrategy(
                        new AirStrategy()
                );
            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/simulacion.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            SimulationController controller =
                    loader.getController();

            controller.setSimulation(
                    simulation
            );

            Stage stage =
                    (Stage) txtAgentes
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

        } catch(Exception e) {

            lblMensaje.setText(
                    "Datos inválidos"
            );

            e.printStackTrace();
        }
    }
}