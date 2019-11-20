package Controller;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentUser;
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
import org.openjfx.DBManager;
import org.openjfx.kunde_windows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * JavaFX App
 */

public class BooksBoard extends Application {
//    Benutzer be = null;
//    public void setBenutzerRun(Benutzer benutzer) throws IOException {
//        be=benutzer;
//        this.start(new Stage());
//    }

    @Override

    public void start(Stage stage) throws IOException, SQLException {
        int Height = 800;
        int Width = 1000;


        Benutzer be = new CurrentUser().getCurrent();
        System.out.println("Booking Overview");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);


        Label text = new Label(
                "Booking Overview");
        text.setFont(Font.font(30));

        Label text1 = new Label(
                "Willkommen " + be.getBenutzername());
        text.setFont(Font.font(25));

        VBox v0 = new VBox();
        v0.setAlignment(Pos.CENTER);
        v0.getChildren().addAll(text, text1);


        ObservableList<String> data = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 500);

        List<Booking> all = new DBManager().getallBookingFromUser(be.getBenutzername());
        for (int i = 0; i < all.size(); i++) {
            Booking ben = all.get(i);
            if(ben.getFluglinie()!=null){
                data.add("ID " + ben.getId()
                        + " von " + ben.getFluglinie().getStart().getCode()
                        + " nach " + ben.getFluglinie().getZiel().getCode()
                        + ". In total " + ben.getFluglinie().getEntfernung().toString()
                        + " with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                        +" " + ben.getFluglinie().getFlugzeug().getType()
                        +" Time: "+ ben.getZeit()
                        +" Class: "+ben.getClasse()
                        +" Seat: "+ben.getSeat()
                        +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                );
            }else{
                data.add("ID " + ben.getId()
                        +" Sorry, this flight was cancelled. "
                        +" Time: "+ ben.getZeit()
                        +" Class: "+ben.getClasse()
                        +" Seat: "+ben.getSeat()
                        +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                );
            }

        }


        listView.setItems(data);


        Button b3 = new Button("Delect");
        b3.setPrefWidth(100);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #7CCD7C;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #5CACEE"
        );
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all= new DBManager().getallBookingFromUser(be.getBenutzername());
                Booking del = all.get(d);
                try {
                    new DBManager().deleteBk(new DBManager().getbkwithbk(del).getId());
                    List<Booking> all1 = new DBManager().getallBookingFromUser(be.getBenutzername());
                    data.clear();
                    for (int i = 0; i < all1.size(); i++) {
                        Booking ben = all1.get(i);
                        if(ben.getFluglinie()!=null){
                            data.add("ID " + ben.getId()
                                    + " von " + ben.getFluglinie().getStart().getCode()
                                    + " nach " + ben.getFluglinie().getZiel().getCode()
                                    + ". In total " + ben.getFluglinie().getEntfernung().toString()
                                    + " with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                                    +" " + ben.getFluglinie().getFlugzeug().getType()
                                    +" Time: "+ ben.getZeit()
                                    +" Class: "+ben.getClasse()
                                    +" Seat: "+ben.getSeat()
                                    +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                            );
                        }else{
                            data.add("ID " + ben.getId()
                                    +" Sorry, this flight was cancelled. "
                                    +" Time: "+ ben.getZeit()
                                    +" Class: "+ben.getClasse()
                                    +" Seat: "+ben.getSeat()
                                    +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                            );
                        }
                    }
                    System.out.println("delected");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                    b3.fire();
                }
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }

            }
        });


        Button b2 = new Button("Buy new ticket?");
        b2.setPrefWidth(180);
        b2.setPrefHeight(20);
        b2.setFont(Font.font(15));
        b2.setStyle("-fx-background-color: #5CACEE;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Welcome kunde");
                Benutzer finalB1 = be;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new CurrentUser().setCurrent(finalB1);
                            new kunde_windows().start(new Stage());
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                });
                stage.close();
            }
        });

        Button b1 = new Button("Refresh");
        b1.setPrefWidth(100);
        b1.setPrefHeight(20);
        b1.setFont(Font.font(15));
        b1.setStyle("-fx-background-color: #7CCD7C;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #5CACEE"
        );

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Booking> all = null;
                all = new DBManager().getallBookingFromUser(be.getBenutzername());
                for (int i = 0; i < all.size(); i++) {
                    Booking ben = all.get(i);
                    if(ben.getFluglinie()!=null){
                        data.add("ID " + ben.getId()
                                + " von " + ben.getFluglinie().getStart().getCode()
                                + " nach " + ben.getFluglinie().getZiel().getCode()
                                + ". In total " + ben.getFluglinie().getEntfernung().toString()
                                + " with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                                +" " + ben.getFluglinie().getFlugzeug().getType()
                                +" Time: "+ ben.getZeit()
                                +" Class: "+ben.getClasse()
                                +" Seat: "+ben.getSeat()
                                +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                        );
                    }else{
                        data.add("ID " + ben.getId()
                                +" Sorry, this flight was cancelled. "
                                +" Time: "+ ben.getZeit()
                                +" Class: "+ben.getClasse()
                                +" Seat: "+ben.getSeat()
                                +" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                        );
                    }
                }
                System.out.println("Refresh");
            }


        });




        HBox butts = new HBox();
        butts.setAlignment(Pos.CENTER);
        butts.getChildren().addAll(b1, b2, b3);

        root.getChildren().addAll(v0, listView, butts);

        Scene scene = new Scene(root);

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Booking Overview " + new CurrentUser().getCurrent().getBenutzername());


    }

}