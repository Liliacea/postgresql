import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

import java.util.ArrayList;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CRUDaoImplTest {


    CRUDaoImpl cruDao;


    @BeforeEach
    void setUp() {
        Connection connection = CRUDaoImpl.getConnection();
        cruDao = new CRUDaoImpl(connection);

    }

    @Test
    void getConnection() {
        assertNotNull(cruDao.connection);
    }


    @Test
    void createTable() {
        long m = System.currentTimeMillis();
        assertThat(cruDao.createTable(), is(true));


        System.out.println(System.currentTimeMillis() - m);
    }

    @Test
    void insert() {
        Person ivan = new Person(1, "ivan", 20, "Mockow", 20000);
        Person Nata = new Person(2, "Nata", 30, "Quatar", 30000);
        long m = System.currentTimeMillis();
        cruDao.createTable();
        cruDao.insert(ivan);
        cruDao.insert(Nata);
        assertThat(cruDao.select().get(0).getName(), is("ivan"));


        System.out.println(System.currentTimeMillis() - m);
    }

    @Test
    void select() {
        ArrayList<Person> people = new ArrayList<>();
        Person ivan = new Person(1, "ivan", 20, "Mockow", 20000);
        Person Nata = new Person(2, "Nata", 30, "Quatar", 30000);
        people.add(ivan);
        people.add(Nata);
        cruDao.createTable();
        cruDao.insert(ivan);


        long m = System.currentTimeMillis();

        assertThat(cruDao.select().size(), is(1));

        System.out.println(System.currentTimeMillis() - m);
    }


    @Test
    void update() {
        Person ivan = new Person(1, "ivan", 20, "Mockow", 20000);
        Person Nata = new Person(2, "Nata", 30, "Quatar", 30000);
        cruDao.createTable();
        cruDao.insert(ivan);
        cruDao.insert(Nata);
        cruDao.update(Nata);

        assertThat(cruDao.select().get(1).getSalary(), is(15000.0));

    }

    @Test
    void delete() {


        Person ivan = new Person(1, "ivan", 20, "Mockow", 20000);
        Person Nata = new Person(2, "Nata", 30, "Quatar", 30000);
        cruDao.createTable();
        cruDao.insert(ivan);
        cruDao.insert(Nata);
        cruDao.delete(ivan);


        assertThat(cruDao.select().size(), is(1));


    }
}




