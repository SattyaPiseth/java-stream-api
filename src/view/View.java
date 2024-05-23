package view;

import exception.InvalidInputException;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class View {
    public static final String WARNING_COLOR = "\033[0;93m";
    public static final String SUCCESS_COLOR = "\033[0;32m";
    public static final String RESET_COLOR = "\033[0m";

    private final Scanner scanner = new Scanner(System.in);


    public Course addNewCourse() {
        int id = new Random().nextInt(99999);
        System.out.print("➡️ Enter course title: ");
        String title = scanner.nextLine().trim();

        System.out.print("➡️ Enter instructor name(s) separated by comma: ");
        String instructorName = scanner.nextLine();

        System.out.print("➡️ Enter course requirement(s) separated by comma: ");
        String requirement = scanner.nextLine();

        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<String> instructors = Arrays.stream(instructorName.split(","))
                .map(String::trim)
                .toList();

        List<String> requirements = Arrays.stream(requirement.split(","))
                .map(String::trim)
                .toList();

        return new Course(id, title, instructors, requirements, startDate);
    }

    public void displayCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.err.println("⛔ No courses available.");
        } else {
            System.out.println(SUCCESS_COLOR+"✅ List of courses:"+RESET_COLOR);
            Table table = buildTableStructure();
            courses.forEach(
                    course -> {
                        addCourseToTable(table,course);
                    }
            );
            System.out.println(table.render());
        }
    }

    public int getOption() {
        System.out.print("Enter option: ");
        try {
            int option;
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            return option;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            throw new InvalidInputException(WARNING_COLOR + "⚠️ Invalid option. Please enter a valid integer." + RESET_COLOR);
        }
    }

    public String getInput() throws InvalidInputException {
        System.out.print("Enter input: ");
        try {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            throw new InvalidInputException(WARNING_COLOR + "⚠️ Input cannot be empty. Please enter a valid input." + RESET_COLOR);
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            throw new InvalidInputException(WARNING_COLOR + "⚠️ Invalid input. Please enter a valid input." + RESET_COLOR);
        }
    }

    public Table buildTableStructure() {

        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        table.setColumnWidth(0, 10, 20);
        table.setColumnWidth(1, 40, 60);
        table.setColumnWidth(2, 60, 80);
        table.setColumnWidth(3, 60, 80);
        table.setColumnWidth(4, 30, 30);

        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Title", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Instructors", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Requirements", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Start Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));

        return table;
    }

    public void addCourseToTable(Table table, Course course) {
        table.addCell(String.valueOf(course.getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(course.getTitle(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(String.join(", ", course.getInstructorName()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(String.join(", ", course.getRequirement()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(course.getStartDate().toString(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
    }

    public void closeScanner() {
        scanner.close();
    }
}
