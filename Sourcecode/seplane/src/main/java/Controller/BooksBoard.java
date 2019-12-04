package Controller;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentUser;
import Toolbox.StringwithArraylist;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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
    TableView<Booking> bookingTable;
    public List<Booking> bookingFromKunde=new List<Booking>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Booking> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Booking booking) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Booking> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Booking> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Booking get(int index) {
            return null;
        }

        @Override
        public Booking set(int index, Booking element) {
            return null;
        }

        @Override
        public void add(int index, Booking element) {

        }

        @Override
        public Booking remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Booking> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Booking> listIterator(int index) {
            return null;
        }

        @Override
        public List<Booking> subList(int fromIndex, int toIndex) {
            return null;
        }
    } ;

    public List<Booking> getBookingFromKunde() {
        return bookingFromKunde;
    }

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

                data.clear();
                List<Booking> all = null;
                all = App.db.getallBookingFromUser(be.getBenutzername());
                ArrayList<Integer> notwo = new ArrayList<Integer>();
                for (int i = 0; i < all.size(); i++) {
                    Booking buchung = all.get(i);
                    if(buchung.getMulti()==null){
                        if(buchung.getFlug()!=null){
                            data.add("BookingID :" + buchung.getId()
                                    + "    Flight von " + buchung.getFluglinie().getStart().getCode()
                                    + " nach " + buchung.getFluglinie().getZiel().getCode()
                                    + ".   In total " + buchung.getFluglinie().getEntfernung().toString()+" KM "
                                    + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                    +"   " + buchung.getFluglinie().getFlugzeug().getType()
                                    +"   Time: "+ buchung.getFlug().getStartzeit()
                                    +"   Class: "+buchung.getClasse()
                                    +"   Seat: "+buchung.getSeat()
                                    +"   Preise: "+Math.round(buchung.getPreise()*100.0)/100.0+""
                            );
                        }else{
                            data.add("BookingID :" + buchung.getId()
                                    +"    Sorry, this flight was cancelled. "
                                    +"   Time: "+ buchung.getFlug().getStartzeit()
                                    +"   Class: "+buchung.getClasse()
                                    +"   Seat: "+buchung.getSeat()
                                    +"   Preise: "+Math.round(buchung.getPreise()*100.0)/100.0+""
                            );
                        }

                    }else{
                        //Multistop
                        String multi= buchung.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        for(int j=0;j<list.size();j++){
                            //String flugid=list.get(j);
                            System.out.println(list.get(j));
                            //int flugid = Integer.parseInt(list.get(j));
                            //buchung=new DBManager().getMultiBook(be.getBenutzername(),flugid);
                            int index = Integer.parseInt(list.get(j));
                            buchung= App.db.getbkId(index);
                            if(!notwo.contains(buchung.getId())){
                                if(j==0){
                                    //Preise Insgesamt
                                    Double preise = 0.0;
                                    for(int y=0;y<list.size();y++){
                                        int ind = Integer.parseInt(list.get(y));
                                        Booking bk = App.db.getbkId(ind);
                                        preise=preise+bk.getPreise();
                                    }

                                    if(buchung.getFlug()!=null){
                                        data.add("BookingID :" + buchung.getId()
                                                +"    Multistop: "+(j+1)+"."
                                                + "   From " + buchung.getFluglinie().getStart().getCode()
                                                + " to " + buchung.getFluglinie().getZiel().getCode()
                                                + ".   In total " + buchung.getFluglinie().getEntfernung().toString()+" KM "
                                                + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                                +"   " + buchung.getFluglinie().getFlugzeug().getType()
                                                +"   Time: "+ buchung.getFlug().getStartzeit()
                                                +"   Class: "+buchung.getClasse()
                                                +"   Seat: "+buchung.getSeat()
                                                +"   Preise in total: "+Math.round(preise*100.0)/100.0+""
                                        );
                                    }else{
                                        //Flight canceled
                                        data.add("BookingID :" + buchung.getId()
                                                +"    Multistop: "+(j+1)+"."
                                                +"    Sorry, this flight was cancelled. "
                                                +"   Time: "+ buchung.getFlug().getStartzeit()
                                                +"   Class: "+buchung.getClasse()
                                                +"   Seat: "+buchung.getSeat()
                                                +"   Preise in total: "+Math.round(preise*100.0)/100.0+""
                                        );
                                    }
                                }else{
                                    if(buchung.getFlug()!=null){
                                        data.add("BookingID :" + buchung.getId()
                                                        +"    Multistop: "+(j+1)+"."
                                                        + "   From " + buchung.getFluglinie().getStart().getCode()
                                                        + "  to " + buchung.getFluglinie().getZiel().getCode()
                                                        + ".   In total " + buchung.getFluglinie().getEntfernung().toString()+" KM "
                                                        + "   with Plane " + buchung.getFluglinie().getFlugzeug().getHersteller()
                                                        +"   " + buchung.getFluglinie().getFlugzeug().getType()
                                                        +"   Time: "+ buchung.getFlug().getStartzeit()
                                                        +"   Class: "+buchung.getClasse()
                                                        +"   Seat: "+buchung.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(buchung.getPreise())*100.0)/100.0+""
                                        );
                                    }else{
                                        data.add("BookingID :" + buchung.getId()
                                                        +"    Multistop: "+(j+1)+"."
                                                        +"   Sorry, this flight was cancelled. "
                                                        +"   Time: "+ buchung.getFlug().getStartzeit()
                                                        +"   Class: "+buchung.getClasse()
                                                        +"   Seat: "+buchung.getSeat()
                                                //+" Preise: "+Math.round(Integer.parseInt(buchung.getPreise())*100.0)/100.0+""
                                        );
                                    }
                                }
                            }

                            notwo.add(buchung.getId());

                        }
                    }


                }
                Benutzer newbe =App.db.getUser(be.getBenutzername());
                text1.setText(
                        "Willkommen " + newbe.getBenutzername()+
                                ".  Ihre CO2 Konto: "+newbe.getco()+" Kg .  Ihre Kilometer Konto: "+newbe.getkilo()+" KM");

                System.out.println("Ihre CO2 Konto: "+newbe.getco()+" Kg .  Ihre Kilometer Konto: "+newbe.getkilo()+" KM");
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
                    if(del.getMulti()==null){
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
                int d = listView.getSelectionModel().getSelectedIndex();
                System.out.println(d);
                List<Booking> all = null;
                bookingFromKunde.clear();
                all= App.db.getallBookingFromUser(be.getBenutzername());
                if(d<=all.size()) {
                    Booking choose = all.get(d);
                    //Singel choose
                    if(choose.getMulti()==null){
                        bookingFromKunde.add(choose);
                    }else{
                        //choose Multistop
                        String multi= choose.getMulti();
                        ArrayList<String> list = new StringwithArraylist().str2list(multi);
                        System.out.println(list);
                        //add all multistops to List
                        for(int i=0;i<list.size();i++){
                            bookingFromKunde.add(App.db.getbkId(Integer.parseInt(list.get(i))));
                        }

                    }
                }
                for(int h=0;h<bookingFromKunde.size();h++){
                    System.out.println(bookingFromKunde.get(h).getSeat());
                }

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