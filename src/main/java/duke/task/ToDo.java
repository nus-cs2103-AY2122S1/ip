package duke.task;

/**
 * Encapsulates a todo task.
 * Todo tasks are tasks without any date/time.
 */
public class ToDo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description description of todo task
     */
    public ToDo(String description) {
        super(description);
        assert !description.equals("") : "ToDo task description cannot be empty";
    }

    /**
     * Gets string representation of todo task.
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
