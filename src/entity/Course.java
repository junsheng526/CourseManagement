package entity;

/**
 *
 * @author Deong Yue Jiaz
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Course implements Comparable<Course>, Serializable {

    private CourseInfo course;
    private LocalDate dateAdded;

    public Course() {

    }

    public Course(String courseCode, String category, String name, int creditHours, String status) {
        this.course = new CourseInfo(courseCode, category, name, creditHours, status);
        this.dateAdded = LocalDate.now();
    }

    public CourseInfo getCourseInfo() {
        return course;
    }

    public void setCourseInfo(CourseInfo course) {
        this.course = course;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    // get positive int when true, get negative integer when false
    // so course A < course B, sequence will be >> A,B
    @Override
    public int compareTo(Course T) {
        return this.course.getCourseCode().compareTo(T.getCourseInfo().getCourseCode());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.course);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course other = (Course) obj;
        return this.course.getCourseCode().equals(other.getCourseInfo().getCourseCode())
                || this.course.getCategory().equals(other.getCourseInfo().getCategory())
                || this.course.getName().equals(other.getCourseInfo().getName())
                || this.course.getStatus().equals(other.getCourseInfo().getStatus());
    }

    @Override
    public String toString() {
        return String.format("%-100s %-15s", course.toString(), dateAdded);
    }
}
