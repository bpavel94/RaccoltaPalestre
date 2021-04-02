import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;





public class BotTelegram extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getText());


    }




    public String getBotUsername() {
        return "Info_Ask_Bot";
    }

    public String getBotToken() {
        return "1651434222:AAF7xL0_2iIcn2SMAip-kh83VXlRJvDu7bs";
    }


}
