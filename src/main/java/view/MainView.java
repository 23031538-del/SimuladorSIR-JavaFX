package view;

import controller.SimulationController;
import model.Simulation;
import Observer.SimulationObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.UUID;

public class MainView implements SimulationObserver {
    private final BorderPane root;
    private final SimulationController controller = new SimulationController();
    private final ObservableList<Simulation> data = FXCollections.observableArrayList();
    private final TableView<Simulation> table = new TableView<>();

    public MainView(model.User user) {
        root = new BorderPane();
        root.setPadding(new Insets(10));

        TableColumn<Simulation, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));

        TableColumn<Simulation, String> statusCol = new TableColumn<>("Estado");
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus().name()));

        TableColumn<Simulation, String> createdCol = new TableColumn<>("Creado");
        createdCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCreatedAt().toString()));

        table.getColumns().addAll(nameCol, statusCol, createdCol);
        table.setItems(data);

        // subscribe to updates
        controller.getService().getObservable().addObserver(this);

        HBox controls = new HBox(10);
        TextField simName = new TextField();
        simName.setPromptText("Nombre simulación");
        Button startBtn = new Button("Iniciar");
        startBtn.setOnAction(e -> {
            UUID id = controller.start(simName.getText(), "{\"param\":\"value\"}");
            Simulation s = controller.get(id);
            data.add(s);
        });

        Button refresh = new Button("Refrescar");
        refresh.setOnAction(e -> {
            data.clear();
            data.addAll(controller.list());
        });

        controls.getChildren().addAll(simName, startBtn, refresh);
        root.setTop(controls);
        root.setCenter(table);
    }

    public Parent getRoot() { return root; }

    @Override
    public void onSimulationUpdated(Simulation simulation) {
        // actualizar la tabla (JavaFX thread)
        javafx.application.Platform.runLater(() -> {
            // buscar y reemplazar
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId().equals(simulation.getId())) {
                    data.set(i, simulation);
                    return;
                }
            }
            // si no existe, agregar
            data.add(simulation);
        });
    }
}
