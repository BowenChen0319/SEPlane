package Models;

import Toolbox.StringwithArraylist;
import org.openjfx.App;

import java.util.*;

public class CurrentBooking extends Booking {
    private static Booking bk;


    public void setBookingFromKunde(Booking bk) {
        CurrentBooking.bk = bk;
        System.out.println(bk);
    }

    public Booking getBookingFromKunde() {
        return bk;
    }

    public List<Booking> getBookingList(){
        //Singel choose
        ArrayList<Booking> bklist = new ArrayList<>();
        if(bk.getMulti()==null){
            bklist.add(bk);
            return bklist;
        }else{
            //choose Multistop
            String multi= bk.getMulti();
            ArrayList<String> list = new StringwithArraylist().str2list(multi);
            System.out.println(list);
            //add all multistops to List
            for(int i=0;i<list.size();i++){
                bklist.add(App.db.getbkId(Integer.parseInt(list.get(i))));
            }
            return bklist;

        }

    }
}

