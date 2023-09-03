package entity;

/**
 *
 * @author Deong Yue Jiaz
 */
import java.io.Serializable;

public class Programme implements Serializable {

    private String code;
    private String name;

    public Programme() {
    }

    public Programme(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(" %-20s %-50s", code, name);
    }
}
