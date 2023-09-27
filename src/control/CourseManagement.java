package control;

/**
 *
 * @author Kai Xin
 */
import adt.*;
import boundary.CourseManagementUI;
import dao.CourseDAO;
import entity.*;
import java.time.LocalDateTime;
import utility.MessageUI;

public class CourseManagement {

    private SortedArrayList<Course> courseList = new SortedArrayList<>();
    private final CourseManagementUI courseUI = new CourseManagementUI();
    private final CourseDAO courseDAO = new CourseDAO();

    public CourseManagement() {
        courseList = courseDAO.retrieveFromFile();
    }

    public void runCourseManagement() {
        int choice;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 0 ->
                    MessageUI.displayExitMessage();
                case 1 -> {
                    addNewCourse();
                    courseUI.listAllCourses(getAllCourses());
                    courseUI.displayMessage("New course added.");
                }
                case 2 -> {
                    removeCourse();
                    courseUI.listAllCourses(getAllCourses());
                }
                case 3 ->
                    findCourse();
                case 4 -> {
                    amendCourseDetails();
                    courseUI.listAllCourses(getAllCourses());
                }
                case 5 ->
                    courseUI.listAllCourses(getAllCourses());
                case 6 ->
                    addProgrammeToCourse();
                case 7 ->
                    removeProgrammeFromCourse();
                case 8 ->
                    listAllProgrammesByCourse();
                case 9 ->
                    displayReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewCourse() {
        Course newCourse = courseUI.inputCourseDetails();
        courseList.add(newCourse);
        courseDAO.saveToFile(courseList);
    }

    public void removeCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseList.remove(course);
            courseDAO.saveToFile(courseList);
            MessageUI.removeCourseSuccessMessage();
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public void findCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseUI.displayCourseDetails(course);
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public void amendCourseDetails() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseUI.displayMessage("### Please enter new details ###");
            Course newCourseDetails = courseUI.inputCourseDetails();
            courseList.replace(course, newCourseDetails);
            courseDAO.saveToFile(courseList);
            MessageUI.updateCourseSuccessMessage();
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public void addProgrammeToCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            Programme newProgramme = courseUI.inputProgrammeDetails();
            course.addProgramme(newProgramme);
            courseDAO.saveToFile(courseList);
            MessageUI.addProgrammeSuccessMessage();
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public void removeProgrammeFromCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            String programmeCode = courseUI.inputProgrammeCode();
            Programme programme = findProgrammeByCode(course, programmeCode);
            if (programme != null) {
                course.getProgrammes().remove(programme);
                courseDAO.saveToFile(courseList);
                MessageUI.removeProgrammeSuccessMessage();
            } else {
                MessageUI.programmeNotFoundMessage();
            }
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public Programme findProgrammeByCode(Course course, String programmeCode) {
        for (int i = 0; i < course.getProgrammes().totalNumberOfObject(); i++) {
            Programme programme = course.getProgrammes().getObject(i);
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }

    public void displayReports() {
        courseUI.listAllCourses(getAllCourses());
        int totalCourse = courseList.totalNumberOfObject();
        Course mostProgrammesCourse = getMostProgrammesByCourse();
        Course fewestProgrammesCourse = getFewestProgrammesByCourse();
        Course recentAddedCourse = getRecentAddedCourse();
        courseUI.displayReport(totalCourse, mostProgrammesCourse, fewestProgrammesCourse, recentAddedCourse);
    }

    // Find Course By Code can reuse in update details also
    private Course findCourseByCode(String courseCode) {
        for (int i = 0; i < courseList.totalNumberOfObject(); i++) {
            Course course = courseList.getObject(i);
            if (course.getCourseInfo().getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private String getAllCourses() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < courseList.totalNumberOfObject(); i++) {
            Course course = courseList.getObject(i);
            outputStr.append(course.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public Course getMostProgrammesByCourse() {
        if (courseList.isListEmpty()) {
            return null; // No courses in the list
        }

        Course mostProgramsCourse = null;
        int maxProgramCount = -1;

        for (int i = 0; i < courseList.totalNumberOfObject(); i++) {
            Course course = courseList.getObject(i);
            int programCount = course.getProgrammeCount();

            if (programCount > maxProgramCount) {
                maxProgramCount = programCount;
                mostProgramsCourse = course;
            }
        }

        return mostProgramsCourse;
    }

    public Course getFewestProgrammesByCourse() {
        if (courseList.isListEmpty()) {
            return null; // No courses in the list
        }

        Course fewestProgramsCourse = null;
        int minProgramCount = Integer.MAX_VALUE; // Set to a high initial value

        for (int i = 0; i < courseList.totalNumberOfObject(); i++) {
            Course course = courseList.getObject(i);
            int programCount = course.getProgrammeCount();

            if (programCount < minProgramCount) { // Check for fewer programs
                minProgramCount = programCount;
                fewestProgramsCourse = course;
            }
        }

        return fewestProgramsCourse;
    }

    public Course getRecentAddedCourse() {
        if (courseList.isListEmpty()) {
            return null; // No courses in the list
        }

        Course recentCourse = null;
        LocalDateTime maxDateTime = LocalDateTime.MIN; // Initialize maxDateTime to a minimum value

        for (int i = 0; i < courseList.totalNumberOfObject(); i++) {
            Course course = courseList.getObject(i);
            LocalDateTime addedDateTime = course.getDateAdded(); // Assuming you have a method to parse the date string

            if (addedDateTime.isAfter(maxDateTime)) {
                maxDateTime = addedDateTime; // Update maxDateTime if a more recent date is found
                recentCourse = course; // Clear the list by creating a new one
            }
        }
        return recentCourse;
    }

    public void listAllProgrammesByCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            String programs = course.listAllProgrammesByCourse();
            if (!programs.isEmpty()) {
                courseUI.listAllProgrammesByCourse(programs);
            } else {
                courseUI.displayMessage("No programs found for Course " + courseCode);
            }
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();
        courseManagement.runCourseManagement();
    }
}
