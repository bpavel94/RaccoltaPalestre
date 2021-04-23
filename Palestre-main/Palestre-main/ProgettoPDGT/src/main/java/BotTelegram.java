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
    
    public BotTelegram(){
        // istanzio la classe che gestisce la connessiona al database
        this.con = new ConnectionDB();
        this.telegramBotsApi = new TelegramBotsApi();
        //creo la connessione con il db
        try {
            this.myConn = con.getConnectionToDB();

           /* if(myConn != null){
               
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
        // se gli passo un altro id mi recupera un altro istruttore, cosi la funzione è dinamica
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
