import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import static org.junit.jupiter.api.Assertions.*;

class DBConfigTest {

    @BeforeEach
    void setUp() {
        Connection connection;
              try {
                  Class.forName("org.postgresql.Driver");
                  Properties prop = new Properties();
                  InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("app.properties");
                  prop.load(input);
                  connection = DriverManager.getConnection
                          (prop.getProperty("db.url"), prop.getProperty("db.user"),
                                  prop.getProperty("db.password"));
                  connection = ConnectionRollBack.create(connection);

              } catch (Exception e){
                  e.printStackTrace();
                  System.out.println("connection doesn't exist");
              }

                Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
    }


    @Test
    void getConnection() {
        assertNotNull(DBConfig.getConnection());
    }

    @Test
    void createTable() {

    }

    @Test
    void insert() {
    }

    @Test
    void select() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}