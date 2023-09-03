package boundary;

/**
 *
 * @author Deong Yue Jiaz
 */
// Ctrl + Shift + I = Organize import
// Alt + Shift + F = Format code
import entity.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(28);
        System.out.println("COURSE MANAGEMENT MENU");
        printLine(28);
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Find Course");
        System.out.println("4. Amend Course Details");
        System.out.println("5. List All Courses");
        System.out.println("6. Add Programme to Course");
        System.out.println("7. Remove Programme from Course");
        System.out.println("8. Generate Reports");
        System.out.println("0. Quit");
        printLine(28);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 8) {
                System.out.println("Invalid choice. Please enter a number between 0 and 8.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }

        scanner.nextLine(); // Consume the newline left in the buffer
        System.out.println(); // Add a blank line after input

        return choice;
    }

    public void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    public void listAllCourses(String outputStr) {
        printLine(112);
        System.out.printf("%-10s %-15s %-40s %-15s %-15s %-15s\n", "Course Code", "Category", "Name", "Credit Hours", "Status", "Date Added");
        printLine(112);
        System.out.print(outputStr);
        printLine(112);
    }

    public Course inputCourseDetails() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course category: ");
        String category = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        int creditHours = -1; // Initialize to a default value
        while (creditHours < 0) {
            try {
                System.out.print("Enter course credit hours: ");
                creditHours = scanner.nextInt();
                if (creditHours < 0) {
                    System.out.println("Credit hours must be a non-negative integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.nextLine(); // Consume the newline left in the buffer
        System.out.print("Enter course status: ");
        String status = scanner.nextLine();

        return new Course(courseCode, category, name, creditHours, status);
    }

    public String inputCourseCode() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        return code;
    }

    public String inputProgrammeCode() {
        System.out.print("Enter programme code: ");
        String code = scanner.nextLine();
        return code;
    }

    public void displayCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course code: " + course.getCourseInfo().getCourseCode());
        System.out.println("Course name: " + course.getCourseInfo().getName());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
