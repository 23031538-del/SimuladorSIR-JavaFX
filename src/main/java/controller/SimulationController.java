package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import model.Agent;
import model.Resultado;
import model.Simulation;
import model.Usuario;

public class SimulationController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label susceptiblesLabel;

    @FXML
    private Label infectadosLabel;

    @FXML
    private Label recuperadosLabel;

    @FXML
    private TableView<Resultado> tablaResultados;

    @FXML
    private TableColumn<Resultado, Integer> colTiempo;

    @FXML
    private TableColumn<Resultado, Integer> colSusceptibles;

    @FXML
    private TableColumn<Resultado, Integer> colInfectados;

    @FXML
    private TableColumn<Resultado, Integer> colRecuperados;

    private Usuario usuario;

    private Simulation simulation;

    private GraphicsContext gc;

    private AnimationTimer timer;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSimulation(Simulation simulation) {

        if (simulation == null) {
            System.out.println("No existe simulación.");
            return;
        }

        this.simulation = simulation;

        System.out.println(
                "Simulación recibida con "
                        + simulation.getAgentes().size()
                        + " agentes."
        );
    }

    @FXML
    private void initialize() {

        gc = canvas.getGraphicsContext2D();

        colTiempo.setCellValueFactory(
                new PropertyValueFactory<>("tiempo")
        );

        colSusceptibles.setCellValueFactory(
                new PropertyValueFactory<>("susceptibles")
        );

        colInfectados.setCellValueFactory(
                new PropertyValueFactory<>("infectados")
        );

        colRecuperados.setCellValueFactory(
                new PropertyValueFactory<>("recuperados")
        );
    }

    @FXML
    public void iniciarSimulacion() {

        if (simulation == null) {

            System.out.println(
                    "No se puede iniciar la simulación."
            );

            return;
        }

        if (timer != null) {
            timer.stop();
        }

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                /*
                 * Cuando implementemos step()
                 * descomenta esta línea:
                 *
                 * simulation.step();
                 */

                dibujar();
            }
        };

        timer.start();

        System.out.println(
                "Simulación iniciada."
        );
    }


    private void dibujar() {

        if (simulation == null) {
            return;
        }

        gc.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        for (Agent agente :
                simulation.getAgentes()) {

            switch (agente.getEstado()) {

                case SUSCEPTIBLE:

                    gc.setFill(Color.GREEN);
                    break;

                case INFECTADO:

                    gc.setFill(Color.RED);
                    break;

                case RECUPERADO:

                    gc.setFill(Color.BLUE);
                    break;
            }

            gc.fillOval(
                    agente.getX(),
                    agente.getY(),
                    8,
                    8
            );
        }

        susceptiblesLabel.setText(
                "S: "
                        + simulation.contarSusceptibles()
        );

        infectadosLabel.setText(
                "I: "
                        + simulation.contarInfectados()
        );

        recuperadosLabel.setText(
                "R: "
                        + simulation.contarRecuperados()
        );

        tablaResultados.getItems().setAll(
                simulation.getHistorial()
        );
    }
}
