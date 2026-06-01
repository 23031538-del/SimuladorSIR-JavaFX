module org.example.modelosir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens controller to javafx.fxml;
    opens model to javafx.fxml;
    opens view to javafx.fxml;

    exports view;
}
