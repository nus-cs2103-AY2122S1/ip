package cygnus.task;

/**
 * Represents a Todo which is a subtype of Task.
 *
 * @author Joshua Yong
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description The description of the Todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Outputs this Todo as a String.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
