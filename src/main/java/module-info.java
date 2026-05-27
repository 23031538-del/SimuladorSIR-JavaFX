module org.epidemia.sir {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.epidemia.sir to javafx.fxml;
    exports org.epidemia.sir;
}