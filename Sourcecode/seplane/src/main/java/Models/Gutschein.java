package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Gutschein {
    @DatabaseField(generatedId=true)
    int id;
    @DatabaseField
    String text;
    @DatabaseField
    Integer percent;

    public Gutschein() {}

    public Gutschein(String text,Integer percent) {
        this.text=text;
        if(percent<=100&&percent>=0){
            this.percent=percent;
        }else{
            this.percent=100;
        }
    }

    public Integer getPercent() {
        return percent;
    }

    public String getText() {
        return text;
    }

    public Boolean checkcodeused(String text){
        List<Gutschein> list = App.db.getGutscheinfromtext(text);
        return !list.isEmpty();
    }
}
