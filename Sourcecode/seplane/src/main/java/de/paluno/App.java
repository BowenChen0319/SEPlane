package de.paluno;

import Models.Flugzeug;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {

    //private static Scene scene;

    
    String password ="1234";
    String username = "admin";


    int Height = 400;
    int Width = 600;
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

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);
        Scene scene = new Scene(root);

        Label text1 = new Label(
                "Willkommen bei SE-Plane");
        text1.setFont(Font.font(25));
        root.getChildren().add(text1);

        Label text2 = new Label(
                "                      " +
                        "deine Flugverwaltungsanwendung :)");
        text2.setFont(Font.font(20));
        root.getChildren().add(text2);


        TextField user = new TextField();
        user.setFont((Font.font(10)));
        root.getChildren().add(user);
        Tooltip tip1 = new Tooltip("Username Please :)");
        tip1.setFont(Font.font(12));
        user.setTooltip(tip1);
        user.setPromptText("Username");
        user.setFocusTraversable(false);

        PasswordField pwd = new PasswordField();
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


        Button b1 = new Button("Login");
        b1.setPrefWidth(100);
        b1.setPrefHeight(20);
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

        Button b2 = new Button("Quit");
        b2.setPrefWidth(100);
        b2.setPrefHeight(20);
        b2.setFont(Font.font(15));
        b2.setStyle("-fx-background-color: #5CACEE;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #7CCD7C"
        );


        Label warning = new Label();
        warning.setFont(Font.font(17));
        root.getChildren().add(warning);

        HBox butt = new HBox();
        butt.setAlignment(Pos.CENTER);
        butt.getChildren().addAll(b1,b2);
        root.getChildren().add(butt);


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(pwd.getText().matches(password)&&user.getText().matches(username)){
                    warning.setText("Welcome");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new dash().start(new Stage());
                                primaryStage.close();
                            } catch (IOException e) {
                                e.printStackTrace();

                            }
                        }
                    });

                }else if(!user.getText().matches(username)){
                    warning.setText("Wrong Username");
                }else if(!pwd.getText().matches(password)&&user.getText().matches(username)){
                    warning.setText("Wrong Password");
                }else{
                    warning.setText("Try again");
                }
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

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        KeyCombination kcb = new KeyCharacterCombination(String.valueOf(KeyCode.Q),KeyCombination.ALT_ANY);
        scene.getAccelerators().put(kcb, new Runnable() {
            @Override
            public void run() {
                System.out.println("key q alt");
            }
        });

        primaryStage.setTitle("SE-Plane Gruppe I");

        //primaryStage.getIcons().add(new Image(path)); //png Bild


        primaryStage.setWidth(Width);
        primaryStage.setHeight(Height);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        //primaryStage.setFullScreen(true);
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

        //Conn c = new Conn();
        //c.getConnection();
        launch();


        DBManager db = new DBManager();
        db.createF(new Flugzeug("Hans", "Hubschrauber", 2.99, 2000.0 ,5));
        //db.createFL(new Fluglinie(new Flughafen(), new Flughafen(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), Intervall.TAG, new Fluggesellschaft(), new Flugzeug(), 0,1,12.0,13.0 ));
        //Fluglinie fl = db.getFLById(1);
        Flugzeug f = db.getFById(0);

        if(f != null)
            System.out.println(f.getFlugzeugtyp());

        //System.out.println(fl.getIntervall() + " "+ fl.getPreisee());


    }



}