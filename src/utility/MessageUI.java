package utility;

/**
 *
 * @author Kai Xin
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void removeCourseSuccessMessage() {
        System.out.println("\nCourse removed successfully.");
    }

    public static void courseNotFoundMessage() {
        System.out.println("\nCourse not found.");
    }

    public static void removeProgrammeSuccessMessage() {
        System.out.println("\nProgramme removed from the course.");
    }

    public static void programmeNotFoundMessage() {
        System.out.println("\nProgramme not found in the course.");
    }

    public static void updateCourseSuccessMessage() {
        System.out.println("\nCourse details amended successfully.");
    }

    public static void addProgrammeSuccessMessage() {
        System.out.println("\nProgramme added to the course.");
    }
}
