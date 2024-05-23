package service;

import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    private List<Course> courses;

    public CourseServiceImpl() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public Optional<Course> findCourseById(Integer id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Course> findCourseByTitle(String title) {
        return courses.stream()
                .filter(course -> course.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public boolean removeCourseById(Integer id) {
        return courses.removeIf(course -> course.getId().equals(id));
    }
}
