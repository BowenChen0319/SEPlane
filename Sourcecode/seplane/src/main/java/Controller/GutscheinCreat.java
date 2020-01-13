package Controller;

import Models.Benutzer;
import Models.CurrentUser;
import Models.Gutschein;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */

public class GutscheinCreat extends Application {


    @Override

    public void start(Stage stage) throws IOException, SQLException {
        int Height = 300;
        int Width = 600;


        Benutzer be = new CurrentUser().getCurrent();
        System.out.println("Gutschein Windows");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);


        Label text = new Label(
                "Gutschein Creat, Willkommen "+ be.getBenutzername());
        text.setFont(Font.font(25));

        HBox h0 = new HBox();
        h0.setAlignment(Pos.CENTER);
        h0.getChildren().addAll(text);

        Label text1 = new Label(
                "Gutschein code you want: ");
        text1.setFont(Font.font(25));
        TextField vorn = new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
                super.replaceText(start, end, text.toLowerCase());
            }
        };
        vorn.setFont((Font.font(10)));
        Tooltip tip1 = new Tooltip("Which code you want? :)");
        tip1.setFont(Font.font(12));
        vorn.setTooltip(tip1);
        vorn.setPromptText("Code");
        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(text1,vorn);


        Label text2 = new Label(
                "Percent: %");
        text2.setFont(Font.font(25));
        Spinner<Integer> nachn = new Spinner<>(0, 100, 1);
//        TextField nachn = new TextField(){
//            @Override
//            public void replaceText(int start, int end, String text) {
//                if (!text.matches("[a-z]")) {
//                    super.replaceText(start, end, text);
//                }
//                if (text.contains(".")) {
//                    super.replaceText(start, end, text);
//                }
//            }
//        };
        //nachn.setFont((Font.font(10)));
        Tooltip tip2 = new Tooltip("How many percent :)");
        tip2.setFont(Font.font(12));
        nachn.setTooltip(tip2);
        nachn.setPromptText("Percent");
        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.getChildren().addAll(text2,nachn);



        Button b3 = new Button("Creat");
        b3.setPrefWidth(150);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );

        Label warning = new Label();
        warning.setFont(Font.font(17));

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBManager db = new DBManager();
                Gutschein g = new Gutschein();
                System.out.println("start Gutschein creat");
//                if(vorn.getText().equals("")||nachn.getText().equals("")){
//                    warning.setText("Code or Percent Please");
//                }else{
//                    int pct = Integer.parseInt(nachn.getText());
//                    if(g.checkcodeused(vorn.getText())==true||pct<0||pct>100){
//                        warning.setText("Warning!");
//                    }else{
//                        try {
//                            db.creategt(new Gutschein(vorn.getText().toLowerCase(),pct));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("Gutschein creat finished");
//                        warning.setText("Gutschein creat finished, please close this window");
//
//
//                    }
//                }
                if(vorn.getText().equals("")){
                    warning.setText("Code or Percent Please");
                }else{
                    int pct = nachn.getValue();
                    if(g.checkcodeused(vorn.getText())==true||pct<0||pct>100){
                        warning.setText("Warning!");
                    }else{
                        try {
                            db.creategt(new Gutschein(vorn.getText().toLowerCase(),pct));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Gutschein creat finished");
                        warning.setText("Gutschein creat finished, please close this window");


                    }
                }
            }
        });


        root.getChildren().addAll(h0,h1,h2,warning,b3);

        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.ENTER){
                    b3.fire();
                }
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    stage.close();
                }

            }
        });

        Scene scene = new Scene(root);

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.getStylesheets().add(App.class.getResource("style.css").toString());
        stage.show();
        stage.setTitle("Gutschein Creat for "+new CurrentUser().getCurrent().getBenutzername());




    }



}





