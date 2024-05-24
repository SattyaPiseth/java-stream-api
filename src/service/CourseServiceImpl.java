package service;

import model.Course;
import repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    @Override
    public void addCourse(Course course) {
        CourseRepository.addCourse(course);
//        courses.add(course);
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(CourseRepository.listCourses());
    }

    @Override
    public Optional<Course> findCourseById(Integer id) {
        return CourseRepository.listCourses().stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Course> findCourseByTitle(String title) {
        return CourseRepository.listCourses().stream()
                .filter(course -> course.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public boolean removeCourseById(Integer id) {
        return CourseRepository.listCourses().removeIf(course -> course.getId().equals(id));
    }
}
