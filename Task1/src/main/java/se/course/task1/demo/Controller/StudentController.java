package se.course.task1.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.course.task1.demo.Entity.Student;
import se.course.task1.demo.Service.StudentServiceImpl;

import java.util.List;

/**
 * @author leaper
 * @description 这是StudentController类
 * @date 2021年 09月29日 09:52:44
 */
@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/api/v1/student")
    public List<Student> getdata() {
        return studentService.getdata();
    }

    @PostMapping("/api/v1/student")
    public String adddata(@RequestBody Student student) {
        String res = studentService.adddata(student);
        return res;
    }

    @DeleteMapping("/api/v1/student/{id}")
    public String deletedata(@PathVariable int id) {
        return studentService.deletedata(id);
    }

    @PutMapping("/api/v1/student/{id}")
    public String modifydata(@PathVariable int id) {
        return studentService.modifydata(id);
    }
}
