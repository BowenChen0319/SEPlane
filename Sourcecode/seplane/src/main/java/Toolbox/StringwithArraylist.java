package Toolbox;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class StringwithArraylist {

    public String list2str(ArrayList<String> list){
        String str = StringUtils.join(list,",");
        return str;
    }

    public ArrayList<String> str2list(String str){
        ArrayList<String> list= new ArrayList<String>(Arrays.asList(str.split(",")));
        return list;
    }



}
