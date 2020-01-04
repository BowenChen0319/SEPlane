package Toolbox;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TelegramMain {

    static int zahl = 200001;
    static boolean storno = true;

    public static void main(String[] args) throws InterruptedException {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TelegramBot bot = new TelegramBot();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }finally {
            new TelegramMain().reactOnStorno(bot);
        }


    }

    public void reactOnStorno(TelegramBot bot) throws InterruptedException {
        String message = "Stornojetzt";
        String chatId = "1057235361";
        
        if (storno) {
            try {
                bot.sendMessage(message, chatId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

    public void test(TelegramBot bot, String text) throws InterruptedException {
        String message = text;
        String chatId = "644260486";

        if (storno) {
            try {
                bot.sendMessage(message, chatId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }
}
