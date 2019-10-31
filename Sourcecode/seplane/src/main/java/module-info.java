module de.paluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires kotlin.stdlib;

    opens de.paluno to javafx.fxml;
    exports de.paluno;
}