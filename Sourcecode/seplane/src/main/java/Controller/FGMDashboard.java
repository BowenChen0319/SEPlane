package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Models.Benutzer;
import Models.CurrentUser;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.openjfx.App;
import org.openjfx.DBManager;
import Models.Fluglinie;
import Toolbox.AlertHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import org.openjfx.login;


public class FGMDashboard implements Initializable {
    String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";
    JdbcPooledConnectionSource cs;


    //Anwendung
    DBManager db = App.db;

    @FXML
    Tab flTab;
    @FXML
    Tab fgTab;
    @FXML
    TabPane FGMTabs;
    //TabController
    @FXML
    FGM_FLDashboard fGM_FluglinieController;
    @FXML
    FGM_FGDashboard fluggesellschaftsmanagerController;

    //Textfield
    @FXML
    TextField messageBox;
    @FXML
    TextField receiverBox;

    Fluglinie curFL;

    Dao<Benutzer, String> bDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fGM_FluglinieController.setParentController(this);
        //TODO setParent Tab 2

    }

    public void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CurrentUser().setCurrent(null);
                    new login().start(new Stage());

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
    }

    public void refresh(ActionEvent event) {
        fGM_FluglinieController.initialize(null, null);
        fluggesellschaftsmanagerController.initialize(null, null);
    }

    public void anlegen(ActionEvent event) throws IOException {
        if (flTab.isSelected()) {
            if (fGM_FluglinieController.fg == null)
                AlertHandler.keineFG();
            else if (fGM_FluglinieController.fList.isEmpty())
                AlertHandler.keineFlugzeuge();
            else
                fGM_FluglinieController.fluglinieAnlegen(event);
        } else if (fgTab.isSelected())
            fluggesellschaftsmanagerController.handleAnlegen();
    }

    public void bearbeiten(ActionEvent event) throws IOException {
        if (flTab.isSelected())
            if (fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
                AlertHandler.keineAuswahl();
            else {
                System.out.println(fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem() + " und " + fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem().getStart());
                curFL = fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem();
                System.out.println(curFL.getStart());
                fGM_FluglinieController.fluglinieBearbeiten(event, fGM_FluglinieController.getRowFL());

            }
        else if (fgTab.isSelected())
            fluggesellschaftsmanagerController.handleBearbeiten();
    }


    public boolean checkIfuserExists(String user, JdbcPooledConnectionSource sc) {
        //String currentUser = new CurrentUser().getCurrent().getBenutzername();
        cs = sc;
        try {
            bDao = DaoManager.createDao(cs, Benutzer.class);
        } catch (SQLException e) {
            System.out.println("Keine Valide Suchanfrage!");

        }

        QueryBuilder<Benutzer, String> queryB = bDao.queryBuilder();
        ObservableList<Benutzer> obBList = FXCollections.observableArrayList();
        List<Benutzer> bListe = null;
        try {
            queryB.where().eq("benutzername", user);
            bListe = bDao.query(queryB.prepare());
            obBList.addAll(bListe);
        } catch (SQLException e) {
            System.out.println("Keine gültige Suchanfrage");
        }
        if (obBList.size() <= 0) {
            return false;
        } else {
            return true;
        }


    }

    public Date convertLocal(LocalDate date) {
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void sendMessage(ActionEvent actionEvent) {
//		System.out.println("user: " + receiverBox.getText());
//		System.out.println("msg: " + messageBox.getText());
        DBManager db = new DBManager();
        //db.sendMessage(receiverBox.getText(), messageBox.getText());
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

        System.out.println(simpleDateFormat.format(d));

        db.sendMessage(receiverBox.getText(), d, messageBox.getText());
    }

    //getter nötig, damit man Im DBmanager auf die messagebox und receiverbox zugreifen kann
    public TextField getMessageBox() {
        return messageBox;
    }

    public TextField getReceiverBox() {
        return receiverBox;
    }

    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }



    public void openFIlex(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FileExplorer.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setTitle("Stage");
            stage.setScene(scene);
            stage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
