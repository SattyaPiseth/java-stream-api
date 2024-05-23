import exception.InvalidInputException;
import model.Course;
import org.nocrala.tools.texttablefmt.Table;
import service.CourseService;
import service.CourseServiceImpl;
import view.View;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final CourseService courseService = new CourseServiceImpl();
    public static  String WARNING_COLOR = "\033[0;93m";
    public static  String SUCCESS_COLOR = "\033[0;32m";
    public static  String RESET_COLOR = "\033[0m";

    public static Integer insert(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\uD83D\uDCDD Insert option: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            throw new InvalidInputException("⚠️ Invalid input. Please enter number instead.");
        }
    }

    public static void border() {
        System.out.println("=".repeat(50));
    }

    public static void menu() {
        border();
        System.out.println("""
                1. Add new Course
                2. List Courses
                3. Find Course BY ID
                4. Find Course BY Title
                5. Remove Course BY ID
                0/99. Exit""");
        border();
    }

    public static void operation() {
        View view = new View();
        while (true) {
            menu();
            try {
                switch (insert()) {
                    case 1 -> {
                        Course newCourse = view.addNewCourse();
                        courseService.addCourse(newCourse);
                    }
                    case 2 -> {
                        view.displayCourses(courseService.listCourses());
                    }
                    case 3 -> {
                        System.out.println("Enter course ID to find: ");
                        int courseId = view.getOption();
                        Optional<Course> foundById = courseService.findCourseById(courseId);
                        foundById.ifPresentOrElse(
                                course -> {

                                Table table = view.buildTableStructure();
                                    view.addCourseToTable(table, course);
                                    System.out.println(table.render());
                                },
                                () -> System.err.println("❌ Course with ID " + courseId + " not found.")
                        );
                    }
                    case 4 -> {
                        System.out.println("Enter course title to find: ");
                        String courseTitle = view.getInput();
                        Optional<Course> foundByTitle = courseService.findCourseByTitle(courseTitle);
                        foundByTitle.ifPresentOrElse(
                                course -> {
                                    Table table = view.buildTableStructure();
                                    view.addCourseToTable(table, course);
                                    System.out.println(table.render());
                                },
                                () -> System.err.println("❌ Course with title '" + courseTitle + "' not found.")
                        );
                    }
                    case 5 -> {
                        System.out.println("Enter course ID to remove: ");
                        int removeId = view.getOption();
                        boolean removed = courseService.removeCourseById(removeId);
                        if (removed) {
                            System.out.println(SUCCESS_COLOR+"✅ Course with ID " + removeId + " removed successfully."+RESET_COLOR);
                        } else {
                            System.err.println("❌ Course with ID " + removeId + " not found.");
                        }
                    }
                    case 0, 99 -> {
                        System.err.println("\uD83D\uDC4B Exiting program. Goodbye! \uD83D\uDD1A❌\uD83C\uDFC3\uD83D\uDEAA");
                        view.closeScanner();
                        System.exit(0);
                    }
                    default -> System.out.println(WARNING_COLOR + "⚠️ Wrong option, try again! ❌" + RESET_COLOR);
                }
            } catch (InvalidInputException e) {
                System.out.println(WARNING_COLOR + e.getMessage() + RESET_COLOR);
            }
        }
    }

    public static void main(String[] args) {
        operation();
    }
}
