package dao;

/**
 *
 * @author Deong Yue Jiaz
 */
import adt.*;
import entity.*;

public class CourseInitializer {

    public SortedListInterface<Course> initializeCourses() {
        SortedListInterface<Course> courseList = new SortedArrayList<>(); // Initialize your SortedArrayList
        courseList.add(new Course("C1001", "FOCS", "Computer Science", 5, "Active"));
        courseList.add(new Course("C1002", "FOET", "Engineering", 5, "Active"));
        courseList.add(new Course("C1003", "FAFB", "Business Administration", 5, "Active"));
        // Add more courses here

        return courseList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeCourses() method
        CourseInitializer courseInitializer = new CourseInitializer();
        SortedListInterface<Course> courseList = courseInitializer.initializeCourses();
        System.out.println("\nCourses:\n" + courseList);
    }
}
