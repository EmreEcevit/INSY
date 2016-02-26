package echo;

import java.sql.*;
import java.time.Clock;

import org.postgresql.ds.PGSimpleDataSource;

public class CRUD {

    public static  String u;
    public static String s;
    public static String p;
    public static String pw;
    public static String d;

    private Connection con;
    private PreparedStatement prep;
    private ResultSet res;
    // create read update delete.
    PGSimpleDataSource ds = new PGSimpleDataSource();

    CRUD(){
        String dbURL = "jdbc:mysql://192.168.98.129/schoko2";
        String username = "schoko2";
        String password = "schoko2";
        try{

            con = DriverManager.getConnection(dbURL, username, password);
            if (con != null) {
                System.out.println("Connected");
            }

        }catch (SQLException exc){
            exc.printStackTrace();
        }
        finally {

        }

    }
    public void insert(){
        try {
            String sql = "INSERT INTO Person (nummer, vorname, nachname) VALUES (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "41");
            statement.setString(2, "Bill");
            statement.setString(3, "Gates");

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("a new Person was inserted");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void select() {
        try {
            String sql = "SELECT * FROM Person";

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                String nummer = result.getString(1);
                String nname = result.getString(3);

                String output = "Person #%d: %s - %s";
                System.out.println(String.format(output, ++count, nummer, nname));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        try {
            String sql = "UPDATE Person SET vorname=?, nachname=? WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "Fabian Lucas");
            statement.setString(2, "Blakes");
            statement.setString(3, "1");

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("an existing person was updated");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        try {
            String sql = "DELETE FROM Person WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "2");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("a person was deleted");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
