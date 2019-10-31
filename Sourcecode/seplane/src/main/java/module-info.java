module de.paluno {
	
	opens de.paluno to javafx.fxml;
	exports de.paluno;
	exports de.paluno.Fluggesellschaftsmanager;
	exports Models;

	requires com.h2database;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	//requires ormlite.core;
	//requires ormlite.jdbc;
	
	

}