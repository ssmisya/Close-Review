package person;

public class CommonPerson extends Employee{
    private boolean hasSuperPower;

    CommonPerson(){}

    CommonPerson(String name, String gender, Long age)
    {
        super(name, gender, age);
        this.hasSuperPower = false;
    }

    public boolean getSuperPower()
    {
        return this.hasSuperPower;
    }
}
