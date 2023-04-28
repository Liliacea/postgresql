import java.sql.*;
import java.util.ArrayList;


public class CRUDaoImpl implements CRUDao{
    Connection connection;
    static Statement statement;
    static String sql;

    public CRUDaoImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Person insert(Person person) {

        try {
            statement = DBConfig.getConnection().createStatement();
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

    return person;
    }

    @Override
    public void delete(Person person) {
        try {
            statement = DBConfig.getConnection().createStatement();
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
    public void update(Person person) {
        try {
            statement = DBConfig.getConnection().createStatement();
            sql = "UPDATE COMPANY set salary = 15000 where ID=2;";
            statement.executeUpdate(sql);

            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("-- Operation UPDATE done successfully");
    }

    @Override
    public boolean  createTable() {
        boolean isCreated = false;
        try {
            statement = DBConfig.getConnection().createStatement();
            sql = "CREATE TABLE public.COMPANY " +
                    "(id INT  PRIMARY KEY     NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    " name           TEXT    NOT NULL, " +
                    " age            INT     NOT NULL, " +
                    " adress        VARCHAR(50), " +
                    " salary         REAL)";
            statement.executeUpdate(sql);
            statement.close();
            isCreated = true;
            System.out.println("-- Table created successfully");
        } catch (Exception e) {
            isCreated = false;
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Table created successfully");
     return isCreated;
    }

    @Override
    public ArrayList<Person> select() {
       ArrayList<Person> people = new ArrayList<>();
        try {
            statement = DBConfig.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String adress = rs.getString("adress");
                float salary = rs.getFloat("salary");
                people.add(new Person(id,name,age,adress,salary));
                System.out.println(String.format("ID=%s NAME=%s AGE=%s ADRESS=%s SALARY=%s", id, name, age, adress, salary));
            }

            rs.close();
            statement.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        System.out.println("-- Operation SELECT done successfully");

       return people;
    }
}
