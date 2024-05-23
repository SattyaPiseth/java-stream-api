package service;

import model.Course;

import java.util.List;
import java.util.Optional;

/**
 * @author Sattya
 * create at 5/23/2024 3:37 PM
 */
public interface CourseService {
    void addCourse(Course course);
    List<Course> listCourses();
    Optional<Course> findCourseById(Integer id);
    Optional<Course> findCourseByTitle(String title);
    boolean removeCourseById(Integer id);
}
