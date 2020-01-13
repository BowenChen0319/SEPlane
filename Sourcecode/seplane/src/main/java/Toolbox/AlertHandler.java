package Toolbox;

import org.openjfx.App;

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
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
		
	}
	//Fluglinie
	public static void keineFG() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Keine Fluggesellschaft");
		alert.setHeaderText("Bitte legen Sie eine Fluggesellschaft an, bevor Sie eine Fluglinie anlegen.");
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}
	//Fluglinie
	public static void keineFlugzeuge() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Keine Flugzeuge");
		alert.setHeaderText("Bitte kaufen Sie ein Flugzeug, bevor Sie eine Fluglinie anlegen.");
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();		
	}

	public static void keineAuswahl() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Fehlerhafte Auswahl");
		alert.setHeaderText("Sie haben keine Auswahl für diese Aktion getroffen.");
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}
	
	//Flugzeug
	public static void kaufenConfirm() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Flugzeug");
		alert.setHeaderText("Das Flugzeug wurde erfolgreich Ihrer Fluggesellschaft hinzugefügt.");
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}

	//Postfach - Kein passender User
	public static void noFittingUser(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kein passender User");
		alert.setHeaderText("Es wurde kein passender User mit diesem Namen gefunden");
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();

	}

	//Flugbuchung
	public static void buchungPerson(int person) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Buchung für Person "+person);
		alert.setHeaderText("Bitte reservieren Sie die Sitzplätze für Person "+person+" , um Ihre Buchung zu vervollständigen.");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}
	public static void buchungConfirm() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Buchung erfolgreich");
		alert.setHeaderText("Sie können sich Ihre Buchungen in der Buchungsübersicht anschauen.");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}

	//PDFExport - Invalider Pfad
	public static void PDFExportPfad(String pfad){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Der eingegebene Pfad ist falsch");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}

	public static void keineNachrichten(String user){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Keine Nachrichten vorhanden");
		alert.setHeaderText("Es sind keine Nachrichtigen vorhanden");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}

	public static void nachrichtGesendet(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erfolgreich!");
		alert.setHeaderText("Die Nachricht wurde erfolgreich versandt");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}

	public static void telegram(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("How to get ChatID!");
		alert.setHeaderText("Um die chatID zu erhalten, müssen sie mittels Telegram einen Chat mit" +
				" unserem Bot \"SepBot\" starten. \n Dort erhalten sie ihre ChatID, welche sie dann in das entsprechende Feld eintragen können.\n   ");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
		alert.showAndWait();
	}



}
