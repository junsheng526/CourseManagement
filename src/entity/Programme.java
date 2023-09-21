package entity;

/**
 *
 * @author Kai Xin
 */
import java.io.Serializable;

public class Programme implements Comparable<Programme>, Serializable {

    private String programmeCode;
    private String name;

    public Programme() {
    }

    public Programme(String programmeCode, String name) {
        this.programmeCode = programmeCode;
        this.name = name;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Programme T) {
        return this.getProgrammeCode().compareTo(T.getProgrammeCode());
    }

    @Override
    public String toString() {
        return String.format("%-20s %-50s", programmeCode, name);
    }
}