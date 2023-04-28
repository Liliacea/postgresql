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
        DBConfig dbConfig = new DBConfig();



        Person ivan = new Person(1, "ivan", 20, "Mockow", 20000);
        Person Nata = new Person(2, "Nata", 30, "Quatar", 30000);
    }


    @Test
    void getConnection() {
        assertNotNull(DBConfig.getConnection());
    }



}