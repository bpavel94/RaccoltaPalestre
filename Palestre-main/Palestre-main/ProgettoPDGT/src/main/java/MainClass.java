import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.*;

public class MainClass {

    public static void main(String[] args) {


        // connessione al bot telegram
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotTelegram());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // connessione al Data Base
        // pass: progettoPGDT
        /*String url = "jdbc:mysql://127.0.0.1:3306/progetto";
        String user = "root";
        String pw = "";
        Connection myCon = null;
        Statement myStmt= null;*/
        ConnectionDB con = new ConnectionDB();
        try {
            Connection myConn = con.getConnectionToDB();
            Statement myStmt = myConn.createStatement();
            // registro JDBC driver
            /*Class.forName("com.mysql.cj.jdbc.Driver");

            // connetto il DB
            System.out.println("Mi sto connettendo al DB...");

            myCon = DriverManager.getConnection(url,user,pw);
            System.out.println("Connesso al DB...");
            myCon.createStatement();
            myStmt = myCon.createStatement();*/



            // posso prendere anche 2 colonne alla volta con un'unica query (esempio: " select nome, cognome from persone ")
            String sql = "select * from palestra";
            ResultSet rs = myStmt.executeQuery(sql);

            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("nome"));
                System.out.println(" - " + rs.getString("orari"));
                System.out.println(" - " + rs.getString("via"));
                System.out.println("\n");
            }

            String sql1 = "select * from istruttore where IDPalestra = 1";
            ResultSet rs1 = myStmt.executeQuery(sql1);

            while (rs1.next()){
                System.out.print(rs1.getString("Nome"));
                System.out.print("  " + rs1.getString("Cognome"));
                System.out.print("\n");
            }

            String sql2 = "select * from corsi where IDISTRUTTORE = 1";
            ResultSet rs2 = myStmt.executeQuery(sql2);

            while (rs2.next()){
                System.out.print(rs2.getString("Nome"));
                System.out.print(" - " + rs2.getString("orario"));
                System.out.print(" - " + rs2.getString("descrizione"));
                System.out.print("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
