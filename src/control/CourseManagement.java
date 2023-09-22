package control;

/**
 *
 * @author Kai Xin
 */
import adt.*;
import boundary.CourseManagementUI;
import dao.CourseDAO;
import entity.*;
import utility.MessageUI;

public class CourseManagement {

    private SortedArrayList<Course> courseList = new SortedArrayList<>();
    private final CourseManagementUI courseUI = new CourseManagementUI();
    private final CourseDAO courseDAO = new CourseDAO();

    int add = 0, remove = 0, edit = 0;

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
                    add++;
                }
                case 2 -> {
                    removeCourse();
                    courseUI.listAllCourses(getAllCourses());
                    remove++;
                }
                case 3 ->
                    findCourse();
                case 4 -> {
                    amendCourseDetails();
                    courseUI.listAllCourses(getAllCourses());
                    edit++;
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

    public void generateReports() {
        courseUI.listAllCourses(getAllCourses());
        int totalCourse = courseList.totalNumberOfObject();
        courseUI.displayCourseManagementReport(totalCourse, add, remove, edit);
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
