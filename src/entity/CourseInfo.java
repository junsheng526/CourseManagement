package entity;

import java.io.Serializable;

/**
 *
 * @author Deong Yue Jiaz
 */
public class CourseInfo implements Serializable {

    private String courseCode;
    private String category;
    private String name;
    private int creditHours;
    private String status;

    public CourseInfo() {

    }

    public CourseInfo(String courseCode, String category, String name, int creditHours, String status) {
        this.courseCode = courseCode;
        this.category = category;
        this.name = name;
        this.creditHours = creditHours;
        this.status = status;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-11s %-15s %-40s %-15s %-15s", courseCode, category, name, creditHours, status);
    }
}
