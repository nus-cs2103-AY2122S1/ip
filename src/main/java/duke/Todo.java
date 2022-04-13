package duke;

/**
 * Represents the Todo type of Task.
 * Todo refer to a type of task that has a string as a description.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     * @param description description for the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean status) {
        super(description, status);
    }

    /**
     * Returns a string form of the Todo task.
     * @return returns the Todo task in string form.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
