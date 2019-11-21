package Controller;

import Models.Booking;
import Models.CurrentUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.openjfx.App;
import org.openjfx.DBManager;

public class BookingOverviewController{

	//Booking
	@FXML TableView<Booking> BookTable;
	@FXML TableColumn <Booking, String> idCol;
	@FXML TableColumn <Booking, String> startCol;
	@FXML TableColumn <Booking, String> zielCol;
	@FXML TableColumn <Booking, String> entfCol;
	@FXML TableColumn <Booking, String> flugzeugCol;
	@FXML TableColumn <Booking, String> zeitCol;
	@FXML TableColumn <Booking, String> sitzCol;
	@FXML TableColumn <Booking, String> preiseCol;
	

	ObservableList<Booking> flList;

	static DBManager db = App.db;

	@FXML
	public void initialize() {
		getInhalte();

		idCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getFlugid() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(Integer.toString(cellData.getValue().getFluglinie().getId()));
		});
		startCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getFluglinie().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getStart().getCode());
		});
		zielCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getFluglinie().getZiel() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getZiel().getCode());
		});
		entfCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getFluglinie().getEntfernung() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getEntfernung().toString());

		});

		flugzeugCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getFluglinie().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getFlugzeug().getHersteller()
						+" " + cellData.getValue().getFluglinie().getFlugzeug().getType());
		});
		zeitCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPreise() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getZeit());
		});
		sitzCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getSeat() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getSeat());

		});
		preiseCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPreise() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(Math.round(Integer.parseInt(cellData.getValue().getPreise())*100.0)/100.0+"");
		});
		
		BookTable.setItems(flList);

	}

	public void getInhalte()  {
		flList = FXCollections.observableArrayList();
		flList.addAll(new DBManager().getallBookingFromUser(new CurrentUser().getCurrent().getBenutzername()));
		System.out.println(flList);
	}


}