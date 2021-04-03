
public class MainClass {
    public static void main(String[] args) {
        // nel main non ci va nulla, mai e poi mai
        // instanzi la classe che si occupa del bot
        BotTelegram bot = new BotTelegram();
        try {

                // fai partire il bot
                // FINE, PORCO DIO NEL MAIN NON CI VA NULLA, NULLAAAAAAAAA
                bot.StartBot();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
