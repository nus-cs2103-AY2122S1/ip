package duke.task;

/**
 * The To-do class encapsulates a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor to initialise a Todo task.
     *
     * @param description The description of a Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
