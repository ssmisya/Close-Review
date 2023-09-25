package person;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class  Employee {

    private @Id @GeneratedValue Long id;
    private String name;
    private String gender;

    private Long age;

    Employee() {}

    Employee(String name, String gender,Long age) {

        this.name = name;
        this.gender = gender;
        this.age = age;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Long age) {
        this.age = age;
    }
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.gender, employee.gender) && Objects.equals(this.age, employee.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.gender, this.age);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", gender='" + this.gender + '\'' + ", age='" + this.age + '\''+ '}';
    }
}