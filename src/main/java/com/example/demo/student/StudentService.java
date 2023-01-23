package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){

        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {

        //Check for duplicate emails
        // If dupllicate then throw exception

        System.out.println("inside add new student");
        System.out.println(student);

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {

        Boolean exists = studentRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("student with id"+id+" does not exists");
        }
        studentRepository.deleteById(id);
    }

    public Student getStudentbyId(Long id) {

        System.out.println(id);

        //return studentRepository.findById(id).get();
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(!optionalStudent.isPresent()){
            throw new UserNotFoundException("id:"+id);
        }

        return optionalStudent.get();

    }

    @Transactional
    public void updateStudentbyId(Long id, String name, Integer marks, Integer age) {

        //Student s= studentRepository.findById(id).get();
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(!optionalStudent.isPresent()){
            throw new UserNotFoundException("id:"+id);
        }

        Student s = optionalStudent.get();

        if(name!=null){
            s.setName(name);
        }
        if(marks!=null){
            s.setMarks(marks);
        }
        if(age!=null){
            s.setAge(age);
        }

    }
}
