import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.*;

public class BotTelegram extends TelegramLongPollingBot {
    private Connection myConn;
    ConnectionDB con;
    TelegramBotsApi telegramBotsApi;
    // Costruttore, quando viene instazionato nel main (ovvero una sola volta all'avvio del programma) vengono valorizzate la variabili
    // per la connessione al database e la classe ConnectionDB dove sono implementati i vari metodi che ci consentono di avere i dati delle nostre palestre
    // il tutto gestito cone una web api che è lo scopo del corso
    // cos'è una web api?
    // è un metodo back end che ti fornisce i dati relativi ad una query, vuoi le palestre, ci deve essere una semplice api chiamate getPalestre che ti dia l'elenco
    // delle palestre
    // vuoi una determinata palestra? di deve avere un altra api chiamate getPalestra che prende in ingresso il parametro IDPalestra e ti fa la query con quel parametro
    // restituendo l'oggetto richiesto
    // le api sono come i camerieri al ristorante, il back end è la cucina e il pranzo è il front end
    // porco dio le api sono la cosa piu importante al mondo
    // porcodioooooooo
    public BotTelegram(){
        // istanzio la classe che gestisce la connessiona al database
        this.con = new ConnectionDB();
        this.telegramBotsApi = new TelegramBotsApi();
        //creo la connessione con il db
        try {
            this.myConn = con.getConnectionToDB();

           /* if(myConn != null){
                //PER ADESSO QUI CI SONO I COMANDI CHE TU STAVI FACENDO NELLA HOME, CIOE' OPERAZIONI A CASO:
                // CIOE' PRENDEVI LE PALESTRE,ISTRUTTORI E CORSI E STAMPAVI I VARI CAMPI
                // CIO' VIENE FATTO ALL'INTERNO DELLE RISPETTIVE FUNZIONI, IL MAIND EVE ESSERE PIU VUOTO POSSIBILE PORCO DIOOOOOO
                operazioniACasoChePoiAndrannoSostituiteConIComandiTelegram();
            }else{
                System.out.print("Non è stato possibile stabilire una connessione con il database.");
            }*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void StartBot() throws SQLException {
        // connessione al bot telegram
        ApiContextInitializer.init();
        //TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        /*try {
            //telegramBotsApi.registerBot(new BotTelegram());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if(myConn != null){
            //PER ADESSO QUI CI SONO I COMANDI CHE TU STAVI FACENDO NELLA HOME, CIOE' OPERAZIONI A CASO:
            // CIOE' PRENDEVI LE PALESTRE,ISTRUTTORI E CORSI E STAMPAVI I VARI CAMPI
            // CIO' VIENE FATTO ALL'INTERNO DELLE RISPETTIVE FUNZIONI, IL MAIND EVE ESSERE PIU VUOTO POSSIBILE PORCO DIOOOOOO
            operazioniACasoChePoiAndrannoSostituiteConIComandiTelegram();
        }else{
            System.out.print("Non è stato possibile stabilire una connessione con il database.");
        }
    }

    public void operazioniACasoChePoiAndrannoSostituiteConIComandiTelegram() throws SQLException {
        // creo lo statement necessario alla convidisione delle mele marce
        Statement myStmt = null;
        try {
            myStmt = myConn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // posso prendere anche 2 colonne alla volta con un'unica query (esempio: " select nome, cognome from persone ")
        ResultSet rs = con.getPalestra(myStmt, 1);
        // seleziono l'istruttore con idpalestra = 1
        // se gli passo un altro id mi recupera un altro istruttore, cosi la funzione è dinamica porcodiooooooo
        ResultSet rs1 = con.getIstruttoreByPalestra(myStmt, 1);
        // recupera i corsi con idistruttore = 1
        ResultSet rs2 = con.getCorsoByIstruttore(myStmt, 4);
    }



    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getText());
        String msg = update.getMessage().getText();
        String chatId=update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }

    }

    public String getBotUsername() {
        return "Info_Ask_Bot";
    }

    public String getBotToken() {
        return "1651434222:AAF7xL0_2iIcn2SMAip-kh83VXlRJvDu7bs";
    }
}
