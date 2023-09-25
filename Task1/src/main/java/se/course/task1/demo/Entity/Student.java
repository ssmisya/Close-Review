package se.course.task1.demo.Entity;

/**
 * @author leaper
 * @description 这是Student类
 * @date 2021年 09月29日 09:52:17
 */
public class Student {
    private int studentID;
    private String name;
    private String department;
    private String major;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
