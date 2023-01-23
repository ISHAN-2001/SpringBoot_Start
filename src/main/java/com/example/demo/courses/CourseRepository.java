package com.example.demo.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {

    @Query("SELECT c FROM Courses c where c.courseName=?1")
    Optional<Courses> findCourse(String courseName);

}
