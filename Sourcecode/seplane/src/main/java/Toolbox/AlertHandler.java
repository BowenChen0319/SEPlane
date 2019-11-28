package Toolbox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class AlertHandler {
	
	//Fluglinie
	public static void falscheAngaben() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fluglinie konnte nicht erstellt werden");
		alert.setHeaderText("Ihre Angaben sind fehlerhaft!");
		alert.setContentText("Bitte prüfen Sie Ihre Eingaben und versuchen Sie es erneut.");
		alert.showAndWait();
	}
	//Fluglinie
	public static void keineFG() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Keine Fluggesellschaft");
		alert.setHeaderText("Bitte legen Sie eine Fluggesellschaft an, bevor Sie eine Fluglinie anlegen.");
		alert.showAndWait();
	}
	//Fluglinie
	public static void keineFlugzeuge() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Keine Flugzeuge");
		alert.setHeaderText("Bitte kaufen Sie ein Flugzeug, bevor Sie eine Fluglinie anlegen.");
		alert.showAndWait();		
	}

	public static void keineAuswahl() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Fehlerhafte Auswahl");
		alert.setHeaderText("Sie haben keine Auswahl für diese Aktion getroffen.");
		alert.showAndWait();
	}
	
	//Flugzeug
	public static void kaufenConfirm() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Flugzeug");
		alert.setHeaderText("Das Flugzeug wurde erfolgreich Ihrer Fluggesellschaft hinzugefügt.");
		alert.showAndWait();
	}

	//Postfach - Kein passender User
	public static void noFittingUser(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kein passender User");
		alert.setHeaderText("Es wurde kein passender User mit diesem Namen gefunden");
		alert.showAndWait();

	}

	//Flugbuchung
	public static void buchungPerson(int person) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Buchung für Person "+person);
		alert.setHeaderText("Bitte reservieren Sie die Sitzplätze für Person "+person+" , um Ihre Buchung zu vervollständigen.");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
	}
}
