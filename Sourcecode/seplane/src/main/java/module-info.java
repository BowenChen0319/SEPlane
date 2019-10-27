module de.paluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    //requires kotlin.stdlib;

    opens de.paluno to javafx.fxml;
    exports de.paluno;
}