package com.example.demo.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Courses> getCourses(){

        return courseRepository.findAll();
    }

    public void postCourses(Courses c){

        Optional<Courses> optionalCourses = courseRepository.findCourse(c.getCourseName());

        if(optionalCourses.isPresent()){
            throw new IllegalStateException("Course already exists");
        }

        courseRepository.save(c);

    }


    public void deleteCourseById(Long id) {

        Optional<Courses> optionalCourses = courseRepository.findById(id);

        if(!optionalCourses.isPresent()){
            throw new IllegalStateException("No course Present");
        }

        courseRepository.deleteById(id);

    }

    @Transactional
    public void updateCourse(Long id, String courseName, String author) {


        if(courseName!=null){

            Optional<Courses> optionalCourses = courseRepository.findCourse(courseName);

            if(optionalCourses.isPresent()){
                throw new IllegalStateException("Course already exists");
            }

            Courses c= courseRepository.findById(id).get();
            c.setCourseName(courseName);

        }

        if(author!=null){

            Courses c= courseRepository.findById(id).get();
            c.setAuthor(author);

        }

    }
}
