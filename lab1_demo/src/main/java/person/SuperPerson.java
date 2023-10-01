package person;

public class SuperPerson extends Employee{
    private boolean hasSuperPower;

    SuperPerson(){}

    SuperPerson(String name, String gender, Long age)
    {
        super(name, gender, age);
        this.hasSuperPower = true;
    }

    public boolean getSuperPower()
    {
        return this.hasSuperPower;
    }

}
