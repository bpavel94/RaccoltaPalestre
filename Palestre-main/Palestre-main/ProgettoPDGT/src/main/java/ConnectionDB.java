import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import org.json.JSONObject;
import java.sql.*;


public class ConnectionDB{

    public Connection getConnectionToDB() throws SQLException, ClassNotFoundException {
        Connection myCon = null;
        try{
            // Non so a cosa serva questa istruzione
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connetto il DB
            System.out.println("Mi sto connettendo al DB...");
            JSONObject json = getCredenziali();
            String user = json.getString("username");
            String pw = json.getString("pw");
            String connectionString = json.getString("connection");
            myCon = DriverManager.getConnection(connectionString, user, pw);
            System.out.println("Connesso al DB...");
            myCon.createStatement();
        }catch(Exception ex){
            System.out.println("An error occurred in getConnectionToDB.");
            ex.printStackTrace();
        }
        return myCon;
    }

    public JSONObject getCredenziali() throws Exception{
        JSONObject json = null;
        String data = "";
        try {
            File myObj = new File("Credenziali.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

            JSONObject json2 = new JSONObject(data);
            json = json2;

        } catch (Exception e) {
            System.out.println("An error occurred in getCredenziali.");
            e.printStackTrace();
        }
        return json;
    }

    //GET PALESTRE
    public ResultSet getPalestre(Statement myStmt) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from palestra";
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("nome"));
                System.out.println(" - " + rs.getString("orari"));
                System.out.println(" - " + rs.getString("via"));
                System.out.println("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getPalestre.");
            ex.printStackTrace();
        }
        return rs;
    }

    // GET SINGOLA PALESTRA
    public ResultSet getPalestra(Statement myStmt, int IDPalestra) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from palestra where idpalestra = " + IDPalestra;
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("nome"));
                System.out.println(" - " + rs.getString("orari"));
                System.out.println(" - " + rs.getString("via"));
                System.out.println("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getPalestra");
            ex.printStackTrace();
        }
        return rs;
    }

    // GET SINGOLO ISTRUTTORE
    public ResultSet getIstruttore(Statement myStmt, int IDIstruttore) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from istruttori where idistruttore = " + IDIstruttore;
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print("  " + rs.getString("Cognome"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getIstruttore.");
            ex.printStackTrace();
        }
        return rs;
    }

    // GET ISTRUTTORI
    public ResultSet getIstruttori(Statement myStmt) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from istruttori";
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print("  " + rs.getString("Cognome"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getIstruttori.");
            ex.printStackTrace();
        }
        return rs;
    }

    // GET CORSI
    public ResultSet getCorsi(Statement myStmt) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from corsi";
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print(" - " + rs.getString("orario"));
                System.out.print(" - " + rs.getString("descrizione"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getCorsi.");
            ex.printStackTrace();
        }
        return rs;
    }

    // GET SINGOLO CORSO
    public ResultSet getCorso(Statement myStmt, int IDCorso) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from corsi where IDCorso = " + IDCorso;
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print(" - " + rs.getString("orario"));
                System.out.print(" - " + rs.getString("descrizione"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getCorso.");
            ex.printStackTrace();
        }
        return rs;
    }

    public ResultSet getCorsoByIstruttore(Statement myStmt, int IDIstruttore) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from corsi where idistruttore = " + IDIstruttore;
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print(" - " + rs.getString("orario"));
                System.out.print(" - " + rs.getString("descrizione"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getCorsoByIstruttore.");
            ex.printStackTrace();
        }
        return rs;
    }

    public ResultSet getIstruttoreByPalestra(Statement myStmt, int IDPalestra) throws SQLException{
        ResultSet rs = null;
        try{
            String sql = "select * from corsi where IDPalestra = " + IDPalestra;
            rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                // devo fare una print per ogni tipo di colonna che mi serve, non posso farli in un'unica riga
                System.out.print(rs.getString("Nome"));
                System.out.print(" - " + rs.getString("orario"));
                System.out.print(" - " + rs.getString("descrizione"));
                System.out.print("\n");
            }

        }catch(Exception ex){
            System.out.println("An error occurred in getIstruttoreByPalestra.");
            ex.printStackTrace();
        }
        return rs;
    }
}
