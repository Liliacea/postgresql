public class Person {
    private int id;
    private String name;
    private int age;
    private String adress;
    private double salary;

    public Person(int id, String name, int age, String adress, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public String getAdress() {
        return adress;
    }


    public double getSalary() {
        return salary;
    }


}
