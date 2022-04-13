package duke.task;

/**
 * A task with no time constraint associated with.
 */
public class Todo extends Task {

    /**
     * Constructs a duke.task.Task object.
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

    /**
     * Produces no effect as Todos do not have dates.
     *
     * @param days Number of days to increase by.
     */
    @Override
    public void increaseDateByDays(int days) {
    }


}
