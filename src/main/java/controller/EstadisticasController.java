package controller;

import dao.SimulacionDAO;
import dao.SimulacionDAOImpl;

import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


import model.Resultado;

import java.util.List;

public class EstadisticasController {

    @FXML
    private LineChart<Number, Number> sirChart;

    @FXML
    public void initialize() {

        cargarDatos();
    }

    private void cargarDatos() {

        try {

            SimulacionDAO dao =
                    new SimulacionDAOImpl();

            List<Resultado> resultados =
                    dao.listar();

            XYChart.Series<Number, Number> serieS =
                    new XYChart.Series<>();

            XYChart.Series<Number, Number> serieI =
                    new XYChart.Series<>();

            XYChart.Series<Number, Number> serieR =
                    new XYChart.Series<>();

            serieS.setName("Susceptibles");
            serieI.setName("Infectados");
            serieR.setName("Recuperados");

            for(Resultado r : resultados) {

                serieS.getData().add(
                        new XYChart.Data<>(
                                r.getTiempo(),
                                r.getSusceptibles()
                        )
                );

                serieI.getData().add(
                        new XYChart.Data<>(
                                r.getTiempo(),
                                r.getInfectados()
                        )
                );

                serieR.getData().add(
                        new XYChart.Data<>(
                                r.getTiempo(),
                                r.getRecuperados()
                        )
                );
            }

            sirChart.getData().clear();

            sirChart.getData().add(serieS);
            sirChart.getData().add(serieI);
            sirChart.getData().add(serieR);

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
