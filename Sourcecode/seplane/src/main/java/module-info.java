module de.paluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires java.sql;
	//requires ormlite.core;
	//requires ormlite.jdbc; muss in Classpath wg. unerlaubtem Package Export (bei alten libs)
	requires com.h2database;

    //requires kotlin.stdlib;

    opens de.paluno to javafx.fxml;
    exports de.paluno;
}