package duke.tasks;

/**
 * Represents To-do task.
 */
public class Todo extends Task {

    /**
     * Constructor for To-do.
     *
     * @param description Description of the To-do.
     * @param isDone      If the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
