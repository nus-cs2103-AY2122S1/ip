package duke.task;

/**
 * A task with no time constraint associated with.
 */
public class Todo extends Task {

    /**
     * Constructor for the duke.task.Task class.
     *
     * @param description Description of the task at hand.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Provides a String representation of the duke.task.Todo.
     *
     * @return A String representation of the duke.task.Todo.
     */
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
