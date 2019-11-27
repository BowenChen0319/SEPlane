package Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Postfach {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String sender;
    @DatabaseField
    private String receiver;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "dd-mm-yyyy")
    private Date date;
    private String dateString;
    @DatabaseField
    private String message;


    public Postfach(){}
    public Postfach(String senderCol, String messageCol, Date date) {

        this.sender = senderCol;
        this.message = messageCol;
        this.date = date;
    }
    public Postfach(String send, String receiver,String mess)
    {
        this.sender = send;
        this.receiver = receiver;
        this.message = mess;
    }

    public Postfach(String sender, String receiver, Date date, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderCol() {
        return sender;
    }

    public void setSenderCol(String sender) {
        this.sender = sender;
    }

    public String getReceiverCol() {
        return receiver;
    }

    public void setReceiverCol(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getMessageCol() {
        return message;
    }

    public void setMessageCol(String message) {
        this.message = message;
    }


}
