module de.paluno.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.paluno.app to javafx.fxml;
    exports de.paluno.app;
}