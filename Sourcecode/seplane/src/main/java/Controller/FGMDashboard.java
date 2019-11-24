package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Models.Benutzer;
import Models.CurrentUser;
import Models.Postfach;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.DBManager;
import Models.Fluglinie;
import Toolbox.AlertHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import org.openjfx.login;
import org.w3c.dom.Text;


public class FGMDashboard implements Initializable{
	String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";
	JdbcPooledConnectionSource cs;



	//Anwendung
	DBManager db = App.db;
	
	@FXML Tab flTab;
	@FXML Tab fgTab;
	@FXML TabPane FGMTabs;
	//TabController
	@FXML FGM_FLDashboard fGM_FluglinieController;
	@FXML FGM_FGDashboard fluggesellschaftsmanagerController;


	//Textfield
	@FXML TextField messageBox;
	@FXML TextField receiverBox;

	//Button
	@FXML Button button_loeschen;
	@FXML Button sendMessageButton;

	Fluglinie curFL;

	Dao<Benutzer, String> bDao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fGM_FluglinieController.setParentController(this);
		//TODO setParent Tab 2
		
		//Blende Löschen-Button aus bei Fluggesellschaft
		FGMTabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue==fgTab)
					button_loeschen.setDisable(true);
				else
					button_loeschen.setDisable(false);
			}
		});
	}

	public void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
		if(flTab.isSelected()) {
			if(fGM_FluglinieController.fg==null)
				AlertHandler.keineFG();
			else if(fGM_FluglinieController.fList.isEmpty())
				AlertHandler.keineFlugzeuge();
			else
				fGM_FluglinieController.fluglinieAnlegen(event);
		}
		else if(fgTab.isSelected()) 
			fluggesellschaftsmanagerController.handleAnlegen();
	}

	public void bearbeiten(ActionEvent event) throws IOException {
		if(flTab.isSelected())
			if(fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else {
				System.out.println(fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem()+" und "+fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem().getStart());
				curFL = fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem();
				System.out.println(curFL.getStart());
				fGM_FluglinieController.fluglinieBearbeiten(event, fGM_FluglinieController.getRowFL());
				
			}
		else if(fgTab.isSelected()) 
			fluggesellschaftsmanagerController.handleBearbeiten();
	}
	public void loeschen(ActionEvent event) throws Exception {
		if(flTab.isSelected()) {
			if(fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else
				fGM_FluglinieController.fluglinieLoeschen(event);
		}
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
	}

	public boolean checkIfuserExists(String user, JdbcPooledConnectionSource sc) {
		//String currentUser = new CurrentUser().getCurrent().getBenutzername();
		cs = sc;
		try {
			bDao = DaoManager.createDao(cs, Benutzer.class);
		}catch(SQLException e){
			System.out.println("Keine Valide Suchanfrage!");

		}

		QueryBuilder<Benutzer, String> queryB = bDao.queryBuilder();
		ObservableList<Benutzer> obBList = FXCollections.observableArrayList();
		List<Benutzer> bListe = null;
		try{
			queryB.where().eq("benutzername", user);
			bListe = bDao.query(queryB.prepare());
			obBList.addAll(bListe);
		}catch(SQLException e)
		{
			System.out.println("Keine gültige Suchanfrage");
		}
		if(obBList.size() <= 0)
		{
			return false;
		}else{
			return true;
		}


	}
	public void sendMessage(ActionEvent actionEvent){
//		System.out.println("user: " + receiverBox.getText());
//		System.out.println("msg: " + messageBox.getText());
		DBManager db = new DBManager();
		db.sendMessage(receiverBox.getText(), messageBox.getText());
	}
	//getter nötig, damit man Im DBmanager auf die messagebox und receiverbox zugreifen kann
	public TextField getMessageBox() {
		return messageBox;
	}
	public TextField getReceiverBox() {
		return receiverBox;
	}


}
