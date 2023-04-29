import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;

import static java.lang.System.getProperty;

public class DBConfig {
    static Connection connection;


    protected static Connection getConnection() {

        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("app.properties")) {
            Class.forName("org.postgresql.Driver");
            Properties prop = new Properties();
            prop.load(input);

            connection = DriverManager.getConnection
                    (prop.getProperty("db.url"), prop.getProperty("db.user"),
                            prop.getProperty("db.password"));


            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection Failed");


        }


        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }


}











