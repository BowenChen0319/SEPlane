
import Toolbox.StringwithArraylist;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StringwithArraylistTest {

    @Test
    void list2str() {
        int anzahl=10;
        ArrayList<String> list = new ArrayList<>();
        String text = "";
        for(int i=0;i<anzahl;i++){
            String str = Integer.toString(i)+" mal "+ RandomStringUtils.randomAlphanumeric(8);
            list.add(str);
            if(i==anzahl-1){
               text=text+str;
            }else{
                text=text+str+",";
            }

        }

        System.out.println(text);
        System.out.println(new StringwithArraylist().list2str(list));
        assertEquals(text,new StringwithArraylist().list2str(list));

    }

    @Test
    void str2list() {
        int anzahl=10;
        ArrayList<String> list = new ArrayList<>();
        String text = "";
        for(int i=0;i<anzahl;i++){
            String str = Integer.toString(i)+" mal "+ RandomStringUtils.randomAlphanumeric(8);
            list.add(str);
            if(i==anzahl-1){
                text=text+str;
            }else{
                text=text+str+",";
            }

        }
        System.out.println(list);
        System.out.println(new StringwithArraylist().str2list(text));
        assertEquals(list,new StringwithArraylist().str2list(text));


    }


}