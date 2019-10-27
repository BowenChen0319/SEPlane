package de.paluno;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    //private static Scene scene;
    int i =0;
    @Override
    public void start(Stage primaryStage) throws IOException {
        //HostServices host = getHostServices();
        //host.showDocument("www.paluno.de");
/*        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("SE-Plane Gruppe I");
        stage.show();*/

//        Button button = new Button("login");
//        button.setPrefWidth(100);
//        button.setPrefHeight(50);

//        URL url = getClass().getClassLoader().getResource("logo.png");
//        String path = url.toExternalForm();

        Group root = new Group();
        Scene scene = new Scene(root);

        PasswordField pwd = new PasswordField();
        //pwd.setText("Password");
        pwd.setLayoutX(200);
        pwd.setLayoutY(250);
        pwd.setPrefWidth(100);
        pwd.setFont((Font.font(10)));
        root.getChildren().add(pwd);
        Tooltip tip = new Tooltip("Password Please :)");
        tip.setFont(Font.font(12));
        pwd.setTooltip(tip);
        pwd.setPromptText("Password");
        pwd.setFocusTraversable(false);
        pwd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.length()>7){
                    pwd.setText(s);
                    System.out.println(s);
                }
            }
        });


        Button b1 = new Button("b1");
        b1.setPrefWidth(50);
        b1.setPrefHeight(50);
        b1.setFont(Font.font(15));
        /*
        b1.setTextFill(Paint.valueOf("#8A2BE2"));
        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#8FBC8F"), new CornerRadii(8), Insets.EMPTY);
        Background bg = new Background(bgf);
        b1.setBackground(bg);
        BorderStroke stk = new BorderStroke(Paint.valueOf("#8A2BE2"),
                BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(3));
        Border bo = new Border(stk);
        b1.setBorder(bo);
        */
        b1.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );

        Button b2 = new Button("b2");
        Button b3 = new Button("b3");



        b1.setLayoutX(0);
        b1.setLayoutY(0);
        b2.setLayoutX(200);
        b2.setLayoutY(0);
        b3.setLayoutX(400);
        b3.setLayoutY(0);
        root.getChildren().addAll(b1,b2,b3);


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                i=i+10;
                Button bu = (Button)actionEvent.getSource();
                if(pwd.getText().matches("1234")){
                    bu.setText(Integer.toString(i/10));
                }

                System.out.println(i+bu.getText());
                Button b4 = new Button(Integer.toString(i/10));
                b4.setLayoutX(400);
                b4.setLayoutY(i);
                root.getChildren().add(b4);

            }
        });

        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                if(mouseEvent.getClickCount()==2 && mouseEvent.getButton().name().equals(MouseButton.PRIMARY.name())){
                    System.out.println("double click");
                }
            }
        });

        b1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().getName());
            }
        });



  /*      root.getChildren().addAll(b1,b2,b3);
        Object[] obj = root.getChildren().toArray();
        for(Object i:obj) {
            Button button = (Button)i;
            button.setPrefWidth(50);
            button.setPrefHeight(50);
        }
*/

//        root.getChildren().add(button);


        KeyCombination kcb = new KeyCharacterCombination(String.valueOf(KeyCode.Q),KeyCombination.ALT_ANY);
        scene.getAccelerators().put(kcb, new Runnable() {
            @Override
            public void run() {
                System.out.println("key q alt");

            }
        });



        Label label = new Label("SEP");
        label.setLayoutX(50);
        label.setLayoutY(200);
        root.getChildren().add(label);
        primaryStage.setTitle("SE-Plane Gruppe I");

        //primaryStage.getIcons().add(new Image(path)); //png Bild


        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        //primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);

        //primaryStage.setOpacity(0.5); //transparency
        //primaryStage.setAlwaysOnTop(true);
        //primaryStage.setX(100);
        //primaryStage.setY(100);
        //primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        //Platform.exit();



        primaryStage.show();


    }

/*    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }*/

    public static void main(String[] args) {
        launch();
    }

}