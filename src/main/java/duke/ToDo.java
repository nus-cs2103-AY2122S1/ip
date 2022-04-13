package duke;

/**
 * Represents a specific type of Task.
 * @author Zhao Peiduo
 */
public class ToDo extends Task {

    /**
     * The constructor for a Todo Object.
     */
    public ToDo(String taskTitle) {
        super(taskTitle);
    }

    /**
     * Customizes the string representation of a todo object.
     *
     * @return string representation of a deadline in the form [T][{X}] {description}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Two todo objects are equal iff they are equal tasks and they are both ToDo objects.
     *
     * @param another the object to be compared with
     * */
    @Override
    public boolean equals(Object another) {
        if (another instanceof ToDo) {
            return super.equals(another);
        }
        return false;
    }
}
