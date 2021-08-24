package duke;

/**
 * Represents a specific type of Task.
 */
public class ToDo extends Task {

    public ToDo(String taskTitle) {
        super(taskTitle);
    }

    /**
     * Customize the string representation of a todo object.
     *
     * @return string representation of a deadline in the form [T][{X}] {description}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
