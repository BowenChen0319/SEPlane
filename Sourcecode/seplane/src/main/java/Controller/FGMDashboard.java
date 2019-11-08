package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.SQLException;

import Models.CurrentUser;
import javafx.application.Application;
import javafx.application.Platform;
import org.openjfx.App;
import org.openjfx.DBManager;

import Models.Fluglinie;
import Toolbox.AlertHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.Window;
import org.openjfx.login;


public class FGMDashboard implements Initializable{
	
	//Anwendung
	DBManager db = App.db;
	//TODO Current User abfragen
	//Benutzer user = App.user;
	
	@FXML Tab flTab;
	@FXML Tab fgTab;
	@FXML TabPane FGMTabs;
	@FXML BorderPane fGM_Fluglinie;
	@FXML FGM_FLDashboard fGM_FluglinieController;
	
	Fluglinie curFL;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		fGM_FluglinieController.setParentController(this);
	}
//	public void injectController(FGM_FLDashboard fg) {
//		this.fGM_FluglinieController = fg;
//	}
	
	//TODO muss hier schon Tabs initialisieren, sonst immer NullPointer bei Tabellenauswahl
	//TODO Test mit static
	public static FGM_FLDashboard fg;

	static {
		try {
			fg = new FGM_FLDashboard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public void anlegen(ActionEvent event) throws IOException {
		if(flTab.isSelected()) {
			
			fGM_FluglinieController.fluglinieAnlegen(event);
			}
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftAnlegen(event);	*/
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
			System.out.println("FG Tab");
			//fluggesellschaftBearbeiten(event);*/
	}
	public void loeschen(ActionEvent event) throws Exception {
		if(flTab.isSelected())
			if(fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else
				fGM_FluglinieController.fluglinieLoeschen(event);
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftLoeschen(event);*/
	}
	
}
