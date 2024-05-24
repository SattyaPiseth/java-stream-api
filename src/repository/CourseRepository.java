package repository;

import model.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sattya
 * create at 5/24/2024 1:31 PM
 */
public class CourseRepository {
    private static final List<Course> allCoursess = new ArrayList<>();

    public static void addCourse(Course course) {
        allCoursess.add(course);
    }

    public static List<Course> listCourses() {
        return allCoursess;
    }
}
