package Toolbox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlertHandler {
	
	//Fluglinie
	public static void falscheAngaben() {
		Alert alert = new Alert(AlertType.ERROR);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		alert.setTitle("Fluglinie konnte nicht erstellt werden");
		alert.setHeaderText("Ihre Angaben sind fehlerhaft!");
		alert.setContentText("Bitte prüfen Sie Ihre Eingaben und versuchen Sie es erneut.");
		alert.showAndWait();
	}

	public static void keineAuswahl() {
		Alert alert = new Alert(AlertType.WARNING);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		alert.setTitle("Fehlerhafte Auswahl");
		alert.setHeaderText("Sie haben keine Auswahl f\u00fcr diese Aktion getroffen.");
		alert.showAndWait();
	}
}
