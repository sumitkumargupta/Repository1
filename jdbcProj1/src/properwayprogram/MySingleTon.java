/**
 * 
 */
package properwayprogram;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySingleTon {
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "test";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";

    private static MySingleTon myObj;   
    private Connection Con ;
    private MySingleTon() {
        System.out.println("Hello");
        Con= createConnection();
    }

    @SuppressWarnings("rawtypes")
    public Connection createConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class driver_class = Class.forName(driver);
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url + dbName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Create a static method to get instance.
     */
    public static MySingleTon getInstance() {
        if (myObj == null) {
            myObj = new MySingleTon();
        }
        return myObj;
    }

    public static void main(String a[]) {
        MySingleTon st = MySingleTon.getInstance();
    }
}