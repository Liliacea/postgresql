import java.io.*;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.System.getProperty;

public class DBConfig {


    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }


        Connection connection = null;


        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("app.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            connection = DriverManager.getConnection
                    (getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));


            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }


        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }


    }
}
