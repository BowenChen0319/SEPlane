package org.openjfx;

import Controller.Adminboard;
import Controller.BooksBoard;
import Models.Benutzer;
import Models.CurrentUser;
import Toolbox.CSVReader;
import Toolbox.Encryption;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */

public class login extends Application {
    int Height = 400;
    int Width = 600;
    public static DBManager db;
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
//        user.setFocusTraversable(false);

        PasswordField pwd = new PasswordField();
        pwd.setFont((Font.font(10)));
        root.getChildren().add(pwd);
        Tooltip tip = new Tooltip("Password Please :)");
        tip.setFont(Font.font(12));
        pwd.setTooltip(tip);
        pwd.setPromptText("Password");
//        pwd.setFocusTraversable(false);
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

//        b1.setTextFill(Paint.valueOf("#8A2BE2"));
//        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#8FBC8F"), new CornerRadii(8), Insets.EMPTY);
//        Background bg = new Background(bgf);
//        b1.setBackground(bg);
//        BorderStroke stk = new BorderStroke(Paint.valueOf("#8A2BE2"),
//                BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(3));
//        Border bo = new Border(stk);
//        b1.setBorder(bo);
//
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

        Button b3 = new Button("Register");
        b3.setPrefWidth(100);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );

        Button b4 = new Button("DB Update");
        b4.setPrefWidth(100);
        b4.setPrefHeight(20);
        b4.setFont(Font.font(15));
        b4.setStyle("-fx-background-color: #5CACEE;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #7CCD7C"
        );


        Label warning = new Label();
        warning.setFont(Font.font(17));
        root.getChildren().add(warning);

        HBox butt = new HBox();
        butt.setAlignment(Pos.CENTER);
        butt.getChildren().addAll(b1,b4,b3,b2);
        root.getChildren().add(butt);


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                warning.setText("Loading");

//                Benutzer b = new Benutzer().getBenutzer(user.getText());
                Benutzer b = null;
                b = new DBManager().getUser(user.getText());
                if(b==null){
                    warning.setText("Wrong Username");

                }else{
                    if(pwd.getText().matches("")||pwd.getText().contains(" ")){
                        warning.setText("Password Please! ");
                    }else{
                        if(b.getBenutzertyp().matches("fgm")){
                            try {
                                if(Encryption.check(pwd.getText(),b.getPasswort())){
                                    warning.setText("Welcome fgm");
                                    System.out.println("Welcome fgm");
                                    user.clear();
                                    pwd.clear();
                                    Benutzer finalB2 = b;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                new CurrentUser().setCurrent(finalB2);
                                                primaryStage.setResizable(true);
                                                Parent fgm1 = FXMLLoader.load(getClass().getResource("FGMDashboardMain.fxml"));
                                                Scene fgmScene = new Scene(fgm1);
                                                Stage stage = primaryStage;

                                                stage.setScene(fgmScene);
                                                fitScreen(stage);
                                                stage.setResizable(true);


                                            } catch (IOException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    });
                                }else{
                                    warning.setText("Wrong Password!");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else if(b.getBenutzertyp().matches("admin")){
                            try {
                                if(Encryption.check(pwd.getText(),b.getPasswort())){
                                    warning.setText("Welcome admin");
                                    System.out.println("Welcome admin");
                                    user.clear();
                                    pwd.clear();
                                    Benutzer finalB = b;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                new CurrentUser().setCurrent(finalB);
                                                new Adminboard().start(new Stage());
                                            } catch (IOException | SQLException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    });
                                    warning.setText("");
                                }else{
                                    warning.setText("Wrong Password!");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else if(b.getBenutzertyp().matches("kunde")){
                            try {
                                if(Encryption.check(pwd.getText(),b.getPasswort())){
                                    warning.setText("Welcome Kunde, Loading......");
                                    System.out.println("Welcome kunde");
                                    user.clear();
                                    pwd.clear();
                                    Benutzer finalB1 = b;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                new CurrentUser().setCurrent(finalB1);
                                                new BooksBoard().start(new Stage());
                                            } catch (IOException | SQLException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    });
                                    warning.setText("");
                                }else{
                                    warning.setText("Wrong Password!");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else{
                            warning.setText("Wrong Password!");
                        }
                    }

                }
            }
        });

//        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                if(mouseEvent.getClickCount()==2 && mouseEvent.getButton().name().equals(MouseButton.PRIMARY.name())){
//                    System.out.println("double click");
//                }
//            }
//        });

//        b1.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                System.out.println(keyEvent.getCode().getName());
//            }
//        });



        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                //beendet FX Applikation, ruft stop() auf und beendet launcher thread
                Platform.exit();
            }
        });

        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.ENTER){
                    b1.fire();
                }
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    b2.fire();
                }
                if(keyEvent.getCode()==KeyCode.F5){
                    try {
                        new Benutzer().showall();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        KeyCombination kcb = new KeyCharacterCombination(String.valueOf(KeyCode.Q),KeyCombination.ALT_ANY);
//        scene.getAccelerators().put(kcb, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("key q alt");
//            }
//        });

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new register().start(new Stage());

                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });


        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                try {
                    DBManager.CSVToDB(CSVReader.OwnCSVReader());
                    new DBManager().addAirportToDb();
                    new login().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
//        static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    public void fitScreen(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
    }

}