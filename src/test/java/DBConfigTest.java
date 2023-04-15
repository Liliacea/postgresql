import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import static org.junit.jupiter.api.Assertions.*;

class DBConfigTest {

    @BeforeEach
    void setUp() {
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