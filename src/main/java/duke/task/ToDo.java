package duke.task;

/**
 * Represents a todo which is a subtype of Task.
 *
 * @author Joshua Yong
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description The description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Outputs this todo as a String.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
