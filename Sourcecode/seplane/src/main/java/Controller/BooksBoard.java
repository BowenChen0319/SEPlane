package Controller;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentBooking;
import Models.CurrentUser;
import Toolbox.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.openjfx.App;
import org.openjfx.kunde_windows;
import org.openjfx.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * JavaFX App
 */

public class BooksBoard extends Application {

    //    Benutzer be = null;
//    public void setBenutzerRun(Benutzer benutzer) throws IOException {
//        be=benutzer;
//        this.start(new Stage());
//    }
    //TableView<Booking> bookingTable;

    @Override

    public void start(Stage stage) throws IOException, SQLException {

        int Height = 700;
        int Width = 1100;


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

        Label text1 = new Label();
        text.setFont(Font.font(25));


        VBox v0 = new VBox();
        v0.setAlignment(Pos.CENTER);
        v0.getChildren().addAll(text, text1);


        ObservableList<String> data = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>();
        listView.setPrefSize(200, 500);


        listView.setItems(data);


        Button b1 = new Button("Refresh");
        b1.setPrefWidth(100);
        b1.setPrefHeight(20);
        b1.setFont(Font.font(15));
        b1.setStyle("-fx-background-color: #5e5cee;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #5CACEE"
        );

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                data.clear();
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                ArrayList<Integer> notwo = new ArrayList<Integer>();
                for (int i = 0; i < all.size(); i++) {
                    Booking buchung = all.get(i);
                    if (buchung.getMulti() == null) {
                        if (buchung.getFlug() != null) {
                            data.add("BookingID :" + buchung.getId()
                                    + "    Flight von " + buchung.getFluglinie().getStart().getCode()
                                    + " nach " + buchung.getFluglinie().getZiel().getCode()
                                    + ".   In total " + buchung.getFluglinie().getEntfernung().toString() + " KM "
                                    + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                    + "   " + buchung.getFluglinie().getFlugzeug().getType()
                                    + "   Time: " + buchung.getFlug().getStartzeit()
                                    + "   Class: " + buchung.getClasse()
                                    + "   Seat: " + buchung.getSeat()
                                    + "   Preise: " + Math.round(buchung.getPreise() * 100.0) / 100.0 + ""
                            );
                        } else {
                            data.add("BookingID :" + buchung.getId()
                                    + "    Sorry, this flight was cancelled. "
                                    + "   Class: " + buchung.getClasse()
                                    + "   Seat: " + buchung.getSeat()
                                    + "   Preise: " + Math.round(buchung.getPreise() * 100.0) / 100.0 + ""
                            );
                        }

                    } else {
                        //Multistop
                        String multi = buchung.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        for (int j = 0; j < list.size(); j++) {
                            //String flugid=list.get(j);
                            System.out.println(list.get(j));
                            //int flugid = Integer.parseInt(list.get(j));
                            //buchung=new DBManager().getMultiBook(be.getBenutzername(),flugid);
                            int index = Integer.parseInt(list.get(j));
                            buchung = App.db.getbkId(index);
                            if (!notwo.contains(buchung.getId())) {
                                if (j == 0) {
                                    //Preise Insgesamt
                                    Double preise = 0.0;
                                    for (int y = 0; y < list.size(); y++) {
                                        int ind = Integer.parseInt(list.get(y));
                                        Booking bk = App.db.getbkId(ind);
                                        preise = preise + bk.getPreise();
                                    }

                                    if (buchung.getFlug() != null) {
                                        data.add("BookingID :" + buchung.getId()
                                                + "    Multi: " + (j + 1) + "."
                                                + "   From " + buchung.getFluglinie().getStart().getCode()
                                                + " to " + buchung.getFluglinie().getZiel().getCode()
                                                + ".   In total " + buchung.getFluglinie().getEntfernung().toString() + " KM "
                                                + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                                + "   " + buchung.getFluglinie().getFlugzeug().getType()
                                                + "   Time: " + buchung.getFlug().getStartzeit()
                                                + "   Class: " + buchung.getClasse()
                                                + "   Seat: " + buchung.getSeat()
                                                + "   Preise in total: " + Math.round(preise * 100.0) / 100.0 + ""
                                        );
                                    } else {
                                        //Flight canceled
                                        data.add("BookingID :" + buchung.getId()
                                                + "    Multi: " + (j + 1) + "."
                                                + "    Sorry, this flight was cancelled. "
                                                + "   Class: " + buchung.getClasse()
                                                + "   Seat: " + buchung.getSeat()
                                                + "   Preise in total: " + Math.round(preise * 100.0) / 100.0 + ""
                                        );
                                    }
                                } else {
                                    if (buchung.getFlug() != null) {
                                        data.add("BookingID :" + buchung.getId()
                                                        + "    Multi: " + (j + 1) + "."
                                                        + "   From " + buchung.getFluglinie().getStart().getCode()
                                                        + "  to " + buchung.getFluglinie().getZiel().getCode()
                                                        + ".   In total " + buchung.getFluglinie().getEntfernung().toString() + " KM "
                                                        + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                                        + "   " + buchung.getFluglinie().getFlugzeug().getType()
                                                        + "   Time: " + buchung.getFlug().getStartzeit()
                                                        + "   Class: " + buchung.getClasse()
                                                        + "   Seat: " + buchung.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(buchung.getPreise())*100.0)/100.0+""
                                        );
                                    } else {
                                        data.add("BookingID :" + buchung.getId()
                                                        + "    Multi: " + (j + 1) + "."
                                                        + "   Sorry, this flight was cancelled. "
                                                        + "   Class: " + buchung.getClasse()
                                                        + "   Seat: " + buchung.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(buchung.getPreise())*100.0)/100.0+""
                                        );
                                    }
                                }
                            }

                            notwo.add(buchung.getId());

                        }
                    }


                }
                Benutzer newbe = App.db.getUser(be.getBenutzername());
                text1.setText(
                        "Willkommen " + newbe.getBenutzername() +
                                ".  Ihre CO2 Konto: " + newbe.getco() + " Kg .  Ihre Kilometer Konto: " + newbe.getkilo() + " KM");

                System.out.println("Ihre CO2 Konto: " + newbe.getco() + " Kg .  Ihre Kilometer Konto: " + newbe.getkilo() + " KM");
                System.out.println("Refresh");
            }


        });


        Button b3 = new Button("Cancel Order");
        b3.setPrefWidth(100);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #bd5cee;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #5CACEE"
        );
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text1.setText("Canceling Order.......");
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking del = all.get(d);
                    ArrayList<Integer> notwo = new ArrayList<Integer>();
                    if (del.getMulti() == null) {
                        try {
                            App.db.deleteBk(App.db.getbkwithbk(del).getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        String multi = del.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        for (int j = 0; j < list.size(); j++) {
                            //String flugid=list.get(j);
                            System.out.println(list.get(j));
                            //int flugid = Integer.parseInt(list.get(j));
                            //ben=new DBManager().getMultiBook(be.getBenutzername(),flugid);
                            int index = Integer.parseInt(list.get(j));
                            //del= new DBManager().getbkId(index);
                            if (!notwo.contains(del.getId())) {
                                try {
                                    App.db.deleteBk(index);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    List<Booking> all1 = App.db.getallBookingFromUser(be.getBenutzername());
                    data.clear();


                    System.out.println("delected");
                    b1.fire();

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
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new CurrentUser().setCurrent(null);
                                new login().start(new Stage());
                            } catch (IOException e) {
                                e.printStackTrace();

                            }
                        }
                    });
                    stage.close();
                }

            }
        });


        Button b2 = new Button("New ticket?");
        b2.setPrefWidth(100);
        b2.setPrefHeight(20);
        b2.setFont(Font.font(15));
        b2.setStyle("-fx-background-color: #e6a243;" +
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

        Button b4 = new Button("Export in PDF");
        b4.setPrefWidth(100);
        b4.setPrefHeight(20);
        b4.setFont(Font.font(15));
        b4.setStyle("-fx-background-color: #5CACEE;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //PDF
                //Set CurrentBooking
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking choose = all.get(d);
                    new CurrentBooking().setBookingFromKunde(choose);
                    System.out.println(choose);
                    //diese bookingFromKunde ist die ausgewählte Booking in List, alles ist vorbereitet,


                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FileExplorer.fxml"));
                        Parent root1 = fxmlLoader.load();
                        Scene scene = new Scene(root1);
                        Stage stage = new Stage();
                        stage.setTitle("PDF");
                        stage.setScene(scene);
                        stage.showAndWait();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        Button b5 = new Button("Video");
        b5.setPrefWidth(100);
        b5.setPrefHeight(20);
        b5.setFont(Font.font(15));
        b5.setStyle("-fx-background-color: #EE5C5C;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Video");
                //Benutzer finalB1 = be;
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking choose = all.get(d);
                    if(choose.getFluglinie()!=null){
                        new CurrentBooking().setBookingFromKunde(choose);
                        System.out.println(choose);
                        Video.setCity(choose.getFluglinie().getZiel().getCity());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //new CurrentUser().setCurrent(finalB1);
                                new Video().start(new Stage());
                            }
                        });
                    }else{
                        text1.setText("Sorry, this flight was cancelled.");
                    }

                }

                //stage.close();
            }
        });

        Button b6 = new Button("Photo");
        b6.setPrefWidth(100);
        b6.setPrefHeight(20);
        b6.setFont(Font.font(15));
        b6.setStyle("-fx-background-color: #ee8a5c;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Photo");
                //Benutzer finalB1 = be;
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking choose = all.get(d);
                    if(choose.getFluglinie()!=null){
                        new CurrentBooking().setBookingFromKunde(choose);
                        System.out.println(choose);
                        Photo.setCity(choose.getFluglinie().getZiel().getCity());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //new CurrentUser().setCurrent(finalB1);
                                new Photo().start(new Stage());
                            }
                        });
                    }else {
                        text1.setText("Sorry, this flight was cancelled.");
                    }
                }

                //stage.close();
            }
        });

        Button b7 = new Button("Hotel");
        b7.setPrefWidth(100);
        b7.setPrefHeight(20);
        b7.setFont(Font.font(15));
        b7.setStyle("-fx-background-color: #e364d8;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hotel");
                //Benutzer finalB1 = be;
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking choose = all.get(d);
                    if(choose.getFluglinie()!=null){
                        new CurrentBooking().setBookingFromKunde(choose);
                        System.out.println(choose);
                        Hotel.setCity(choose.getFluglinie().getZiel().getCity());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //new CurrentUser().setCurrent(finalB1);
                                new Hotel().start(new Stage());
                            }
                        });
                    }else{
                        text1.setText("Sorry, this flight was cancelled.");
                    }
                }

                //stage.close();
            }
        });

        Button b8 = new Button("Restaurant");
        b8.setPrefWidth(100);
        b8.setPrefHeight(20);
        b8.setFont(Font.font(15));
        b8.setStyle("-fx-background-color: #e36464;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );

        b8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Restaurant");
                //Benutzer finalB1 = be;
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    Booking choose = all.get(d);
                    if(choose.getFluglinie()!=null){
                        new CurrentBooking().setBookingFromKunde(choose);
                        System.out.println(choose);
                        Restaurant.setCity(choose.getFluglinie().getZiel().getCity());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //new CurrentUser().setCurrent(finalB1);
                                new Restaurant().start(new Stage());
                            }
                        });
                    }else{
                        text1.setText("Sorry, this flight was canceled.");
                    }

                }

                //stage.close();
            }
        });
        Button b9 = new Button("Route");
        b9.setPrefWidth(100);
        b9.setPrefHeight(20);
        b9.setFont(Font.font(15));
        b9.setStyle("-fx-background-color: #5CACEE;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #7CCD7C"
        );
        b9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Route");
                //Benutzer finalB1 = be;
                int d = listView.getSelectionModel().getSelectedIndex();
                Double lat, lon;
                ;
                System.out.println("Selected index: " + d);
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                if (d <= all.size() && d != -1) {
                    RoutenansichtController route = new RoutenansichtController();
                    Booking choose = all.get(d);
                    new CurrentBooking().setBookingFromKunde(choose);
                    System.out.println(choose);
                    lat = choose.getFluglinie().getStart().getLat();
                    lon = choose.getFluglinie().getStart().getLon();


                    try {
                        System.out.println("Aus Booksboard: " + lat + " " + lon);
                        route.setZielLat(lat);
                        route.setZielLon(lon);
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Routenansicht.fxml"));
                        Parent root1 = fxmlLoader.load();
                        Scene scene = new Scene(root1);
                        Stage stage = new Stage();
                        stage.setTitle("Route");
                        stage.setScene(scene);
                        stage.showAndWait();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //stage.close();
            }
        });


        b1.fire();


        HBox butts = new HBox();
        butts.setAlignment(Pos.CENTER);
        butts.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);

        root.getChildren().addAll(v0, listView, butts);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource("style.css").toString());

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Booking Overview " + new CurrentUser().getCurrent().getBenutzername());


    }


}