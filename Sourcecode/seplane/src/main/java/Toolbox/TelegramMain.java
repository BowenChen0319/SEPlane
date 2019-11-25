package Toolbox;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneId;

public class TelegramMain {

    static int zahl = 200001;
    public static void main(String[] args) throws InterruptedException {




        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TelegramBot bot = new TelegramBot();
        TelegramMain maaainn = new TelegramMain();
        try{
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        while(zahl >= 2000)
        {
            maaainn.reactOnStorno(bot);

        }

    }

    public void reactOnStorno(TelegramBot bot) throws InterruptedException {
        String message = "Testnachricht";
        String chatId = "1057235361";
        int counter = 27;

       for(int i=0; i<=30;i++)
       {
           if(counter == i)
           {
               try {
                   bot.sendMessage(message + " Tst",chatId);
               } catch (TelegramApiException e) {
                   e.printStackTrace();
               }
           }else {
               System.out.println("Counter: " + i + " " + " Zeitpunkt: " + LocalTime.now(ZoneId.systemDefault()));
           }
           Thread.sleep(2000);
       }


    }


}
