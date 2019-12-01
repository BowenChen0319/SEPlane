package Controller;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentUser;
import Toolbox.PDFExport;
import Toolbox.StringwithArraylist;
import com.itextpdf.text.DocumentException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.openjfx.kunde_windows;
import org.openjfx.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    TableView<Booking> bookingTable;
    @Override

    public void start(Stage stage) throws IOException, SQLException {

        int Height = 700;
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
                "Willkommen " + be.getBenutzername()+
                ".  Ihre CO2 Konto: "+be.getco()+" Kg .  Ihre Kilometer Konto: "+be.getkilo()+" KM");
        text.setFont(Font.font(25));

        VBox v0 = new VBox();
        v0.setAlignment(Pos.CENTER);
        v0.getChildren().addAll(text, text1);


        ObservableList<String> data = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 500);





        listView.setItems(data);

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
                all = App.db.getallBookingFromUser(be.getBenutzername());
                ArrayList<Integer> notwo = new ArrayList<Integer>();
                for (int i = 0; i < all.size(); i++) {
                    Booking ben = all.get(i);
                    if(ben.getMulti().equals("")||ben.getMulti().equals(",")){
                        if(ben.getFlug()!=null){
                            data.add("BookingID :" + ben.getId()
                                    + "    Flight von " + ben.getFluglinie().getStart().getCode()
                                    + " nach " + ben.getFluglinie().getZiel().getCode()
                                    + ".   In total " + ben.getFluglinie().getEntfernung().toString()+" KM "
                                    + "   with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                                    +"   " + ben.getFluglinie().getFlugzeug().getType()
                                    +"   Time: "+ ben.getFlug().getStartzeit()
                                    +"   Class: "+ben.getClasse()
                                    +"   Seat: "+ben.getSeat()
                                    +"   Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                            );
                        }else{
                            data.add("BookingID :" + ben.getId()
                                    +"    Sorry, this flight was cancelled. "
                                    +"   Time: "+ ben.getFlug().getStartzeit()
                                    +"   Class: "+ben.getClasse()
                                    +"   Seat: "+ben.getSeat()
                                    +"   Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                            );
                        }

                    }else{
                        String multi= ben.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        for(int j=0;j<list.size();j++){
                            //String flugid=list.get(j);
                            System.out.println(list.get(j));
                            //int flugid = Integer.parseInt(list.get(j));
                            //ben=new DBManager().getMultiBook(be.getBenutzername(),flugid);
                            int index = Integer.parseInt(list.get(j));
                            ben= App.db.getbkId(index);
                            if(!notwo.contains(ben.getId())){
                                if(j==0){
                                    if(ben.getFlug()!=null){
                                        data.add("BookingID :" + ben.getId()
                                                +"    Multistop: "+(j+1)+"."
                                                + "   From " + ben.getFluglinie().getStart().getCode()
                                                + " to " + ben.getFluglinie().getZiel().getCode()
                                                + ".   In total " + ben.getFluglinie().getEntfernung().toString()+" KM "
                                                + "   with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                                                +"   " + ben.getFluglinie().getFlugzeug().getType()
                                                +"   Time: "+ ben.getFlug().getStartzeit()
                                                +"   Class: "+ben.getClasse()
                                                +"   Seat: "+ben.getSeat()
                                                +"   Preise in total: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                                        );
                                    }else{
                                        data.add("BookingID :" + ben.getId()
                                                +"    Multistop: "+(j+1)+"."
                                                +"    Sorry, this flight was cancelled. "
                                                +"   Time: "+ ben.getFlug().getStartzeit()
                                                +"   Class: "+ben.getClasse()
                                                +"   Seat: "+ben.getSeat()
                                                +"   Preise in total: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                                        );
                                    }
                                }else{
                                    if(ben.getFlug()!=null){
                                        data.add("BookingID :" + ben.getId()
                                                        +"    Multistop: "+(j+1)+"."
                                                        + "   From " + ben.getFluglinie().getStart().getCode()
                                                        + "  to " + ben.getFluglinie().getZiel().getCode()
                                                        + ".   In total " + ben.getFluglinie().getEntfernung().toString()+" KM "
                                                        + "   with Plane " + ben.getFluglinie().getFlugzeug().getHersteller()
                                                        +"   " + ben.getFluglinie().getFlugzeug().getType()
                                                        +"   Time: "+ ben.getFlug().getStartzeit()
                                                        +"   Class: "+ben.getClasse()
                                                        +"   Seat: "+ben.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                                        );
                                    }else{
                                        data.add("BookingID :" + ben.getId()
                                                        +"    Multistop: "+(j+1)+"."
                                                        +"   Sorry, this flight was cancelled. "
                                                        +"   Time: "+ ben.getFlug().getStartzeit()
                                                        +"   Class: "+ben.getClasse()
                                                        +"   Seat: "+ben.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(ben.getPreise())*100.0)/100.0+""
                                        );
                                    }
                                }
                            }

                            notwo.add(ben.getId());

                        }
                    }


                }
                text1.setText(
                        "Willkommen " + be.getBenutzername()+
                                ".  Ihre CO2 Konto: "+be.getco()+" Kg .  Ihre Kilometer Konto: "+be.getkilo()+" KM");

                System.out.println("Refresh");
            }


        });


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
                text1.setText("Delecting.......");
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                all= App.db.getallBookingFromUser(be.getBenutzername());
                if(d<=all.size()){
                    Booking del = all.get(d);
                    ArrayList<Integer> notwo = new ArrayList<Integer>();
                    if(del.getMulti().equals("")){
                        try {
                            App.db.deleteBk(App.db.getbkwithbk(del).getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        String multi= del.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        for(int j=0;j<list.size();j++){
                            //String flugid=list.get(j);
                            System.out.println(list.get(j));
                            //int flugid = Integer.parseInt(list.get(j));
                            //ben=new DBManager().getMultiBook(be.getBenutzername(),flugid);
                            int index = Integer.parseInt(list.get(j));
                            //del= new DBManager().getbkId(index);
                            if(!notwo.contains(del.getId())){
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

        Button b4 = new Button("Export in PDF");
        b4.setPrefWidth(180);
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
                String pfad = System.getProperty("user.dir"); //mit filexplorer tauschen
                List<Booking> all = App.db.getallBookingFromUser(be.getBenutzername());
                try {

                    new PDFExport().createPdf(pfad);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }


            }
        });





        b1.fire();




        HBox butts = new HBox();
        butts.setAlignment(Pos.CENTER);
        butts.getChildren().addAll(b1, b2, b3,b4);

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