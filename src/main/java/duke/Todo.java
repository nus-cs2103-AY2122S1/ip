package duke;

/**
 * Encapsulates a todo item, which can be entered into the to-do-list.
 */
public class Todo extends Task {
    /**
     * Constructor for a task to be done.
     *
     * @param description A short description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a task to be done.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the task, in a format suitable for storing in a text file.
     *
     * @return The string representation of the task, in a format suitable for storing in a text file.
     */
    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
