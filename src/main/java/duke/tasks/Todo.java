package duke.tasks;

/**
 * Represents Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description of the Todo.
     * @param isDone if the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
