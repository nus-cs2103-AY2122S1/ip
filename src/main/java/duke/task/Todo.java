package duke.task;

/**
 * A task that is to be done.
 */
public class Todo extends Task {
    /**
     * A constructor used to initialize the todo.
     *
     * @param description the description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A constructor used to initialize the todo through file input.
     *
     * @param description the description of the todo.
     * @param isCompleted the state of the todo.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    public void updateTodo(Todo updatedTodo) {
        this.description = updatedTodo.description;
    }

    /**
     * Returns the string representation of todo.
     *
     * @return the string representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of todo for file input/output.
     *
     * @return the string representation of todo for file input/output.
     */
    @Override
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        return String.format("%s | %s | %s", "T", displayCompletion, this.description);
    }
}
