import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CRUDaoImplTest {

//Connection connection ;
ArrayList <Person> person = new ArrayList<>();
    CRUDaoImpl cruDao;



    @BeforeEach
    void setUp() {
    Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            Properties prop = new Properties();
            InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("app.properties");
            prop.load(input);
            connection = DriverManager.getConnection
                    (prop.getProperty("db.url"), prop.getProperty("db.user"),
                            prop.getProperty("db.password"));
            connection = ConnectionRollBack.create(connection);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection doesn't exist");
        }
       cruDao = new CRUDaoImpl(connection);

    /*    Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
        cruDao.createTable();
        cruDao.insert(ivan);
        cruDao.insert(Nata);

     */
    }

    @Test
    void getConnection() {
        assertNotNull(cruDao.connection);
    }



    @Test
    void createTable() {
        long m = System.currentTimeMillis();
        assertThat(cruDao.createTable(), is(true));


        System.out.println(System.currentTimeMillis()-m);
    }

    @Test
    void insert() {
        Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
        long m = System.currentTimeMillis();
        assertThat(cruDao.insert(ivan).getName(),is("ivan"));



        System.out.println(System.currentTimeMillis()-m);
    }

    @Test
    void select() {
        ArrayList<Person> people = new ArrayList<>();
        Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);

        cruDao.insert(ivan);


        long m = System.currentTimeMillis();

        assertThat(cruDao.select(), is(people));

        System.out.println(System.currentTimeMillis()-m);
    }


    @Test
    void update() {
        Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
        cruDao.insert(ivan);
        cruDao.insert(Nata);
        cruDao.update(Nata);

        assertThat(cruDao.select().get(2), is(15000));

    }

    @Test
    void delete() {

        ArrayList<Person> people = new ArrayList<>();
        Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
        cruDao.insert(ivan);
        cruDao.insert(Nata);
        cruDao.delete(ivan);




        assertNull(cruDao.select().get(1));


    }
    }




