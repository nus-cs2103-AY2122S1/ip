package duke.task;

/**
 * Todos are a type of task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo.
     *
     * @param description The description associated with the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of task.
     *
     * @return "T" representing the task is a Todo.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Pretty-prints the Todo in a readable way.
     *
     * @return The string representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
