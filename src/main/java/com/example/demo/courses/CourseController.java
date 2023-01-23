package com.example.demo.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/courses")
public class CourseController {

    private CourseService courseservice;

    @Autowired
    public CourseController(CourseService courseservice){
        this.courseservice = courseservice;
    }

    @GetMapping
    public List<Courses> getCourses(){
        return  courseservice.getCourses();
    }

    @PostMapping
    public void postCourses(@RequestBody Courses course){

        if(course.getCourseName()==null){
            throw new IllegalStateException("No course Name");
        }
        if(course.getAuthor()==null){
            throw new IllegalStateException("No author");
        }

        courseservice.postCourses(course);
    }

    @DeleteMapping("delete/{courseId}")
    public void deletecourse(@PathVariable("courseId") Long Id){

        courseservice.deleteCourseById(Id);
    }

    @PutMapping("update/{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long Id,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String author){

        System.out.println(String.format("%d %s %s",Id,courseName,author));

        courseservice.updateCourse(Id,courseName,author);

    }

}
