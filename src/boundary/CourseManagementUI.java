package boundary;

/**
 *
 * @author Kai Xin
 */
import entity.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("COURSE MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Find Course");
        System.out.println("4. Amend Course Details");
        System.out.println("5. List All Courses");
        System.out.println("6. Add Programme to Course");
        System.out.println("7. Remove Programme from Course");
        System.out.println("8. List All Programmes By Course");
        System.out.println("9. Generate Report");
        System.out.println("0. Quit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 9) {
                System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println();

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
        printLine(137);
        System.out.printf("%-10s %-15s %-40s %-15s %-15s %-25s %-15s\n", "Course Code", "Category", "Name", "Credit Hours", "Status", "Date Added", "Programmes");
        printLine(137);
        System.out.print(outputStr);
        printLine(137);
    }

    public void listAllProgrammesByCourse(String outputStr) {
        printLine(70);
        System.out.printf("%-20s %-50s\n", "Programme Code", "Name");
        printLine(70);
        System.out.print(outputStr);
        printLine(70);
    }

    private boolean isValidCourseCode(String code) {
        return code.matches("B[A-Z]{3}\\d{4}");
    }

    private boolean isValidCourseCategory(String code) {
        return code.matches("^F[A-Z]{3}$");
    }

    private boolean isValidProgrammeCode(String code) {
        return code.matches("^R[A-Z]{2}Y\\d{1}S\\d{1}$");
    }

    public Course inputCourseDetails() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g., BACS1234, BAIT0987): ");
            courseCode = scanner.nextLine().trim().toUpperCase();
            if (isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
        String category;
        while (true) {
            System.out.print("Enter course category (e.g., FOCS, FCCI): ");
            category = scanner.nextLine().trim().toUpperCase();
            if (isValidCourseCategory(category)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course category.");
            }
        }
        String name;
        while (true) {
            System.out.print("Enter course name: ");
            name = scanner.nextLine().trim().toUpperCase();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }

        int creditHours = -1; // Initialize to a default value
        while (creditHours < 0) {
            try {
                System.out.print("Enter course credit hours: ");
                creditHours = Integer.parseInt(scanner.nextLine());
                if (creditHours < 0) {
                    System.out.println("Credit hours must be a non-negative integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        String status;
        while (true) {
            System.out.print("Enter course status: ");
            status = scanner.nextLine().trim().toUpperCase();
            if (!status.isEmpty()) {
                break;
            } else {
                System.out.println("Status cannot be empty!");
            }
        }

        return new Course(courseCode, category, name, creditHours, status);
    }

    public Programme inputProgrammeDetails() {
        String programmeCode;
        while (true) {
            System.out.print("Enter programme code (e.g., RISY1Y1, RSWY2S3): ");
            programmeCode = scanner.nextLine().trim().toUpperCase();
            if (isValidProgrammeCode(programmeCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid programme code.");
            }
        }
        String name;
        while (true) {
            System.out.print("Enter programme name: ");
            name = scanner.nextLine().trim().toUpperCase();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }

        return new Programme(programmeCode, name);
    }

    public String inputProgrammeCode() {
        String programmeCode;
        while (true) {
            System.out.print("Enter programme code (e.g., RISY1Y1, RSWY2S3): ");
            programmeCode = scanner.nextLine().trim().toUpperCase();
            if (isValidProgrammeCode(programmeCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid programme code.");
            }
        }
        return programmeCode;
    }

    public String inputCourseCode() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g., BACS1234, BAIT0987): ");
            courseCode = scanner.nextLine().trim().toUpperCase();
            if (isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
        return courseCode;
    }

    public void displayCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course code: " + course.getCourseInfo().getCourseCode());
        System.out.println("Course name: " + course.getCourseInfo().getName());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayReport(int totalCourses, Course mostProgrammesCourse, Course fewestProgrammesCourse, Course recentAddedCourse) {
        printLine(70);
        System.out.println("\t\tCourse Management Report");
        printLine(70);
        System.out.println("Total Number of Courses             >> " + totalCourses);
        System.out.println("The course with most programmes     >> " + mostProgrammesCourse.getCourseInfo().getCourseCode() + "\t" + mostProgrammesCourse.getProgrammeCount() + " programme(s)");
        System.out.println("The course with fewest programmes   >> " + fewestProgrammesCourse.getCourseInfo().getCourseCode() + "\t" + fewestProgrammesCourse.getProgrammeCount() + " programme(s)");
        System.out.println("The recent added course             >> " + recentAddedCourse.getCourseInfo().getCourseCode() + "\t" + recentAddedCourse.getCourseInfo().getName());
        printLine(70);

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }
}
