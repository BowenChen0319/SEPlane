package Controller;

import Models.Postfach;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.App;

import java.net.URL;
import java.util.ResourceBundle;

public class KundenNachrichtenController implements Initializable {

    @FXML
    private TextArea messageField;
    @FXML
    private TextField senderField;


    private static String message;
    private static String sender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageField.setWrapText(true);

        messageField.setText(message);
        senderField.setText(sender);
    }

    @FXML
    public void extendMessage() {
        Kunde_FlugbuchungController k = new Kunde_FlugbuchungController();
//        String selectedMsg = message;
//        String selectedSender = sender;
//        System.out.println("Msg: " + selectedMsg + " Sender: " + selectedSender);

        try {


            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MessageWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
