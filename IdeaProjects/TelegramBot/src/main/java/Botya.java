import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Botya extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Botya());
        }
        catch (TelegramApiException e){
            e.printStackTrace();

        }

    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message !=null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Нужна помощь?");
                            break;
                case "/setting":
                    sendMsg(message, "Что настроим?");
                    break;
                default:

            }
        }

    }

    public String getBotUsername() {
        return "BotyaWeather";
    }

    public String getBotToken() {
        return "1415770720:AAE5OOv0BcsyuWJys3OTF4Ocv75Qv7MRrZ0";
    }
}
