import java.util.ArrayList;
import java.util.List;

public class Check {
    public static void main(String[] args) {
        CRUDaoImpl cruDao = new CRUDaoImpl(DBConfig.getConnection());

       // cruDao.createTable();
        Person ivan = new Person(1,"ivan",20, "Mockow", 20000);
        Person Nata = new Person(2,"Nata",30,"Quatar",30000);
        ArrayList<Person> people = new ArrayList<>();
        people.add(Nata);
        people.add(ivan);
        long m = System.currentTimeMillis();

        cruDao.insert(Nata);
        cruDao.insert(ivan);

        cruDao.select();


        System.out.println(System.currentTimeMillis()-m);


        // DBConfig.insert(Nata);


       /* DBConfig.select();
        DBConfig.update();
        DBConfig.select();
        DBConfig.delete();
        DBConfig.select();

        */


    }
}
