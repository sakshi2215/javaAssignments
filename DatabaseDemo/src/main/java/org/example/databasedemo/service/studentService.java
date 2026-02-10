package org.example.databasedemo.service;

import org.example.databasedemo.model.Student;
import org.example.databasedemo.repo.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {

    private studentRepo repo;
    public void addStudent(Student s){
        repo.save(s);
    }
    public List<Student> getStudents(){
        return repo.findAll();
    }
    public studentRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(studentRepo repo) {
        this.repo = repo;
    }
}
