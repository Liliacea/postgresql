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


/*
    @Override
    public void insert(Person person) {
        try {
            statement = getConnection().createStatement();
            sql = "INSERT INTO COMPANY (name, age, adress, salary) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getAdress());
            preparedStatement.setDouble(4, person.getSalary());

            preparedStatement.executeUpdate();



            statement.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("-- Records created successfully");

    }

    @Override
    public void delete() {
        try {
            statement = getConnection().createStatement();
            sql = "DELETE from COMPANY where ID=1;";
            statement.executeUpdate(sql);

            statement.close();
            System.out.println("-- Operation DELETE done successfully");


            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update() {
        try {
            statement = getConnection().createStatement();
            sql = "UPDATE COMPANY set salary = 25000 where ID=2;";
            statement.executeUpdate(sql);

            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("-- Operation UPDATE done successfully");
    }

    @Override
    public void createTable() {
        try {
            statement = getConnection().createStatement();
            sql = "CREATE TABLE public.COMPANY " +
                    "(id INT  PRIMARY KEY     NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    " name           TEXT    NOT NULL, " +
                    " age            INT     NOT NULL, " +
                    " adress        VARCHAR(50), " +
                    " salary         REAL)";
            statement.executeUpdate(sql);
            statement.close();
            // connection.commit();
            System.out.println("-- Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Table created successfully");


    }

    @Override
    public void select() {
        try {
            statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("adress");
                float salary = rs.getFloat("salary");
                System.out.println(String.format("ID=%s NAME=%s AGE=%s ADRESS=%s SALARY=%s", id, name, age, address, salary));
            }
            rs.close();
            statement.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("-- Operation SELECT done successfully");

    }


    /*
    public static void createTable() {
        try {
            statement = getConnection().createStatement();
            sql = "CREATE TABLE public.COMPANY " +
                    "(id INT  PRIMARY KEY     NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    " name           TEXT    NOT NULL, " +
                    " age            INT     NOT NULL, " +
                    " adress        VARCHAR(50), " +
                    " salary         REAL)";
            statement.executeUpdate(sql);
            statement.close();
           // connection.commit();
            System.out.println("-- Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

            System.out.println("Table created successfully");



    }

    public static void insert(Person person) {
        try {
            statement = getConnection().createStatement();
            sql = "INSERT INTO COMPANY (name, age, adress, salary) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getAdress());
            preparedStatement.setDouble(4, person.getSalary());

           preparedStatement.executeUpdate();



            statement.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("-- Records created successfully");
    }

    public static void select() {
        try {
            statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("adress");
                float salary = rs.getFloat("salary");
                System.out.println(String.format("ID=%s NAME=%s AGE=%s ADRESS=%s SALARY=%s", id, name, age, address, salary));
            }
            rs.close();
            statement.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("-- Operation SELECT done successfully");
    }

    public static void update() {
        try {
            statement = getConnection().createStatement();
            sql = "UPDATE COMPANY set salary = 25000 where ID=2;";
            statement.executeUpdate(sql);

            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("-- Operation UPDATE done successfully");
    }

    public static void delete() {
        try {
            statement = getConnection().createStatement();
            sql = "DELETE from COMPANY where ID=1;";
            statement.executeUpdate(sql);

            statement.close();
            System.out.println("-- Operation DELETE done successfully");


            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }

     */

    }











