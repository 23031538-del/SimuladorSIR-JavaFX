module SIRSimulatorFX {

    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.desktop;

    opens controller to javafx.fxml;

    opens model to javafx.base;

    exports app;
    exports controller;
    exports model;
    exports dao;
    exports database;
    exports crypto;
    exports factory;
    exports strategy;
    exports observer;
}