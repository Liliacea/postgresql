import java.util.ArrayList;

public interface CRUDao {

    public Person insert(Person person);

    public void delete(Person person);
    public void update(Person person);
    public boolean createTable();
    public ArrayList<Person>  select();

}
