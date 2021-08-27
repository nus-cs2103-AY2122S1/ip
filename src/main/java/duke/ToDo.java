package duke;

/**
 * Represents a task to be completed.
 */
public class ToDo extends Task{
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * String representation of todo task.
     *
     * @return String form of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
