import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.json.JSONObject;
import java.sql.*;


public class ConnectionDB{
    Connection myCon = null;
    //Statement myStmt= null;

    public Connection getConnectionToDB() throws SQLException, ClassNotFoundException {
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
        return myCon;
    }

    public JSONObject getCredenziali(){
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

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return json;
    }
}
