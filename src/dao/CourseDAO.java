package dao;

/**
 *
 * @author Deong Yue Jiaz
 */
import entity.*;
import java.io.*;
import adt.*;

public class CourseDAO {

    private String fileName = "courses.dat";

    public void saveToFile(SortedArrayList<Course> courseList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SortedArrayList<Course> retrieveFromFile() {
        File file = new File(fileName);
        SortedArrayList<Course> courseList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (SortedArrayList<Course>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }
    }
}
