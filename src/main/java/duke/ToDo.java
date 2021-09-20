package duke;

/**
 * Represents a task to be completed.
 */
public class ToDo extends Task{
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Represents Todo task as a String object.
     *
     * @return String form of Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
