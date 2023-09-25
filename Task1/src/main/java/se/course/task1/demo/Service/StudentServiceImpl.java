package se.course.task1.demo.Service;

import org.springframework.stereotype.Service;
import se.course.task1.demo.Entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leaper
 * @description 这是StudentServiceImpl类
 * @date 2021年 09月29日 09:53:26
 */
@Service
public class StudentServiceImpl {

    //返回student信息列表
    public List<Student> getdata() {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setStudentID(1);
        s1.setName("Jack");
        s1.setDepartment("cs");
        s1.setMajor("data");
        students.add(s1);

        Student s2 = new Student();
        s2.setStudentID(1);
        s2.setName("Mike");
        s2.setDepartment("se");
        s2.setMajor("nlp");
        students.add(s2);
        return students;
    }

    public String adddata(Student student){
        return "sucesss";
    }

    public String deletedata(int id){
        return "sucesss";
    }

    public String modifydata(int id){
        return "success";
    }
}
