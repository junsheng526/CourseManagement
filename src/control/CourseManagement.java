package control;

/**
 *
 * @author Deong Yue Jiaz
 */
import adt.*;
import boundary.CourseManagementUI;
import dao.CourseDAO;
import entity.Course;
import utility.MessageUI;

public class CourseManagement {

    private SortedArrayList<Course> courseList = new SortedArrayList<>();
    private CourseManagementUI courseUI = new CourseManagementUI();
    private CourseDAO courseDAO = new CourseDAO();

    public CourseManagement() {
        courseList = courseDAO.retrieveFromFile();
    }

    public void runCourseManagement() {
        int choice = 0;
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
                case 4 ->
                    amendCourseDetails();
                case 5 ->
                    courseUI.listAllCourses(getAllCourses());
                case 6 ->
                    addProgrammeToCourse();
                case 7 ->
                    removeProgrammeFromCourse();
                case 8 ->
                    generateReports();
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
            courseUI.displayMessage("Course removed.");
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void findCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseUI.displayCourseDetails(course);
        } else {
            courseUI.displayMessage("Course not found.");
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
            courseUI.displayMessage("Course details amended.");
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void addProgrammeToCourse() {
        // Implement adding a programme to a course
        // This functionality will depend on your specific requirements
    }

    public void removeProgrammeFromCourse() {
        // Implement removing a programme from a course
        // This functionality will depend on your specific requirements
    }

    public void generateReports() {
        // Implement generating relevant reports
        // This functionality will depend on the reports you need to generate
    }

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

    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();
        courseManagement.runCourseManagement();
    }
}
