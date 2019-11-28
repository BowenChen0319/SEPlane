package Toolbox;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class StringwithArraylist {

    public String strtoalist(ArrayList<String> list){
        String str = StringUtils.join(list,",");
        return str;
    }

    public ArrayList<String> alisttostr(String str){
        ArrayList<String> list= new ArrayList<String>(Arrays.asList(str.split(",")));
        return list;
    }

}
