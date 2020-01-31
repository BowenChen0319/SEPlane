package Toolbox;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    String chatIDsdf = ""; //insert ChatID

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendMessage(String message, String chatID) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(chatID);
        sendMessage.setText(message);
        execute(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return "SEPlane_Bot";
    }

    @Override
    public String getBotToken() {
        return "InsertTelegramKeyHere";
    }

    public boolean flugStorniert(){
        return true;
    }


}
