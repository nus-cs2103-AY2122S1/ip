/**
 * A Task object represent a task in Duke
 * It is simply a string which describes the task to be done
 */

public class Task {

    // The string that describes the task
    String desc;

    Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
