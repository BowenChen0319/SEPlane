package Toolbox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {
	
	//Fluglinie
	public static void falscheAngaben() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fluglinie konnte nicht erstellt werden");
		alert.setHeaderText("Ihre Angaben sind fehlerhaft!");
		alert.setContentText("Bitte prüfen Sie Ihre Eingaben und versuchen Sie es erneut.");
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
}
