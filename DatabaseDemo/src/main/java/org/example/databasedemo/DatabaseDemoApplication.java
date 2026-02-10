package org.example.databasedemo;

import org.example.databasedemo.model.Student;
import org.example.databasedemo.service.studentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DatabaseDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(DatabaseDemoApplication.class, args);

        Student s = context.getBean(Student.class);
        s.setName("Sakshi");
        s.setMarks(30);
        s.setRollNo(34);

        studentService service = context.getBean(studentService.class);
        service.addStudent(s);
        List<Student> students = service.getStudents();
        System.out.println(students);

    }

}
