package dao;

/**
 *
 * @author Kai Xin
 */
import adt.*;
import entity.*;

public class CourseInitializer {

    public ListInterface<Course> initializeCourses() {
        ListInterface<Course> courseList = new SortedArrayList<>(); // Initialize your SortedArrayList
        courseList.add(new Course("BACS1013", "FOCS", "PROBLEM SOLVING AND PROGRAMMING", 3, "ACTIVE"));
        courseList.add(new Course("BACS2023", "FOCS", "OBJECT-ORIENTED PROGRAMMING", 3, "ACTIVE"));
        courseList.add(new Course("BHEL1013", "FCCI", "ENGLISH LANGUAGE", 3, "ACTIVE"));
        courseList.add(new Course("MPU-3322", "FSSH", "CONTEMPORARY MALAYSIAN ISSUES", 2, "ACTIVE"));
        courseList.add(new Course("BAMS1623", "FOET", "DISCRETE MATHEMATICS", 3, "ACTIVE"));
        courseList.add(new Course("BAIT2113", "FOCS", "WEB APPLICATION DEVELOPMENT", 3, "ACTIVE"));
        // Add more courses here

        return courseList;
    }

    public static void main(String[] args) {
        CourseInitializer courseInitializer = new CourseInitializer();
        ListInterface<Course> courseList = courseInitializer.initializeCourses();
        System.out.println("\nCourses:\n" + courseList);
    }
}
