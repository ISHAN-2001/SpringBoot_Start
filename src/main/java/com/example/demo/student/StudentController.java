package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="/student")
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){

        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path="/getbyId/{studentId}")
    public Student getStudentbyId(@PathVariable("studentId") Long id){

        return studentService.getStudentbyId(id);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){

        System.out.println("inside register student");
        //System.out.println(student);
        studentService.addNewStudent(student);

        /*System.out.println(student);
        return "Message is "+student;*/
    }



    @DeleteMapping(path="/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name ,
                              @RequestParam(required = false) Integer marks,
                              @RequestParam(required = false) Integer age){ //method start

        System.out.println("id= "+id+", name="+name+", marks="+marks+", age="+age);

        studentService.updateStudentbyId(id,name,marks,age);

    }

}
