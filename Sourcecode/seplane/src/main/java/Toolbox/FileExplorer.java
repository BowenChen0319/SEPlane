package Toolbox;


import com.itextpdf.text.DocumentException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FileExplorer implements Initializable {

    @FXML
    TextField pfadText;
    @FXML
    TableView<String> ordnerTable;
    @FXML
    TableColumn<String, String> nameCol;

    ObservableList<String> fileList;

    public static String lastFolder;
    public static String currentFolder;
    public static String nextFolder;
    public static final String userDir = System.getProperty("user.home");


    public void saveSelected(ActionEvent actionEvent) {
        String dest = currentFolder;
        try {
            new PDFExport().createPdf(dest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void backToLastFolder(ActionEvent actionEvent) {
        currentFolder = lastFolder;
        refreshTable(lastFolder);
    }

    public ObservableList<String> getListOfFiles(String path) {
        File dir = new File(path);
        fileList = FXCollections.observableArrayList();
        String[] arr = dir.list();
        fileList.addAll(Arrays.asList(arr));
//        for(String s : fileList)
//        {
//            System.out.println(s);
//        }
        return fileList;
    }


    public void refreshTable(ActionEvent event) {
        currentFolder = pfadText.getText();
        ObservableList<String> list = FXCollections.observableArrayList();

        list.addAll(getListOfFiles(currentFolder));
        ordnerTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nameCol.setCellValueFactory(new PropertyValueFactory<>("nameCol"));
        pfadText.setText(userDir);
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        ordnerTable.setRowFactory(tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    String rowData = row.getItem();

                    nextFolder = currentFolder + ("\\" + rowData);
                    refreshTable(nextFolder);
                    lastFolder = currentFolder;
                    currentFolder = nextFolder;
                    refreshTable(currentFolder);
                    System.out.println("NF: " + nextFolder);
                    System.out.println("CF: " + currentFolder);
                    System.out.println("LF: " + lastFolder);
                }
            });
            return row;
        });
    }

    //refresh doppelklick in der Tabelle
    private void refreshTable(String nextFolder) {
        System.out.println("Naechster Ordner: " + nextFolder);
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(getListOfFiles(nextFolder));
        ordnerTable.setItems(list);
    }
}
