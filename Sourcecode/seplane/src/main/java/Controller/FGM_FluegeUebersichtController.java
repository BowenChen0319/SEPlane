package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FGM_FluegeUebersichtController {

    @FXML
    private TableView<?> fluegeUebersicht_table;

    @FXML
    private TableColumn<?, ?> startflughafen_column;

    @FXML
    private TableColumn<?, ?> zielflughafen_column;

    @FXML
    private TableColumn<?, ?> intervall_column;

    @FXML
    private TableColumn<?, ?> jungfernflug_column;

    @FXML
    private TableColumn<?, ?> Flugzeug_column;

    @FXML
    private TableColumn<?, ?> economyPlaetze_column;

    @FXML
    private TableColumn<?, ?> businessPlaetze_column;

    @FXML
    private TableColumn<?, ?> preisEconomy_column;

    @FXML
    private TableColumn<?, ?> preisBusiness_column;

}
