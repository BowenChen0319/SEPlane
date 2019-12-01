package Toolbox;


import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileExplorer{

    @FXML TextField pfadText;
    @FXML TableView<String> ordnerTable;


//    public static void main(String[] args) {
//        new FileExplorer()
//    }
    public static String lastFolder = System.getProperty("user.dir");

    public void saveSelected(ActionEvent actionEvent) {
        String dest = pfadText.getText();
        try {
            new PDFExport().createPdf(dest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void backToLastFolder(ActionEvent actionEvent) {

    }

    public ObservableList<String> getListOfFiles(String path){
        File dir = new File(path);
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] arr = dir.list();
        list.addAll(Arrays.asList(arr));
        return list;
    }


    public void refreshTable(ActionEvent actionEvent) {
        ObservableList<String> list = getListOfFiles(lastFolder);
        ordnerTable.setItems(list);
    }
}
