package Controller;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentUser;
import Models.Gutschein;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.openjfx.App;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * JavaFX App
 */

public class Gutscheinboard extends Application {

    @Override

    public void start(Stage stage) throws IOException, SQLException {
        int Height = 400;
        int Width = 600;


        Benutzer be = new CurrentUser().getCurrent();
        System.out.println("Gutschein management window");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);


        Label text = new Label(
                "Gutschein management");
        text.setFont(Font.font(30));

        Label text1 = new Label(
                "Willkommen "+ be.getBenutzername());
        text.setFont(Font.font(25));

        VBox v0 = new VBox();
        v0.setAlignment(Pos.CENTER);
        v0.getChildren().addAll(text,text1);


        ObservableList<String> data = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);

        Button b1 = new Button("Refresh");
        b1.setPrefWidth(100);
        b1.setPrefHeight(20);
        b1.setFont(Font.font(15));
        b1.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                data.clear();
                List<Gutschein> all = App.db.getallGutschein();
                for(int i=0;i<all.size();i++){
                    Gutschein ben = all.get(i);
                    data.add("ID "+ben.getId()
                            +" Code: "+ben.getText()
                            +" with "+ben.getPercent()
                            +"%");
                }
                listView.setItems(data);
                System.out.println("Refresh");
            }
        });

        b1.fire();


        Button b3 = new Button("Delect");
        b3.setPrefWidth(100);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int d=listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Gutschein> all = null;
                all = App.db.getallGutschein();
                if(d<=all.size()){
                    Gutschein del = all.get(d);
                    try {
                        App.db.deleteGT(del.getId());
                        b1.fire();
                        System.out.println("delected");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.BACK_SPACE){
                    b3.fire();
                }
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    stage.close();
                }

            }
        });


        Button b2 = new Button("New Gutschein");
        b2.setPrefWidth(120);
        b2.setPrefHeight(20);
        b2.setFont(Font.font(15));
        b2.setStyle("-fx-background-color: #5CACEE;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #7CCD7C"
        );

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new GutscheinCreat().start(new Stage());
                            //stage.close();

                        } catch (IOException | SQLException e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });






        HBox butts = new HBox();
        butts.setAlignment(Pos.CENTER);
        butts.getChildren().addAll(b1,b2,b3);

        root.getChildren().addAll(v0,listView,butts);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource("style.css").toString());

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("FGM Creat for "+new CurrentUser().getCurrent().getBenutzername());




    }



}





