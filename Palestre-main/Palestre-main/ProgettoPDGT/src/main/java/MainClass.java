
public class MainClass {
    public static void main(String[] args) {
        
        // instanzio la classe che si occupa del bot
        BotTelegram bot = new BotTelegram();
        try {

                // faccio partire il bot
                bot.StartBot();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
