package Toolbox;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    String chatIDsdf = "1057235361";

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
        return "1045586215:AAG_QDa31xpAMsQbFX2-4B0VSmKKipS_sDE";
    }

    public boolean flugStorniert(){
        return true;
    }


}
