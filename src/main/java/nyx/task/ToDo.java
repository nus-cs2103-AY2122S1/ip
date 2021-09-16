package nyx.task;

/**
 * Represents a task with no deadline or time of occurrence.
 * This class inherits from the parent abstract Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo task with its description and an indicator of whether it is marked as done.
     *
     * @param content Description of the todo task.
     * @param isDone Indicator to indicate whether the todo task is done.
     */
    public ToDo(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Constructs an uncompleted todo task with its description.
     *
     * @param content Description of the todo task.
     */
    public ToDo(String content) {
        this(content, false);
    }

    /**
     * Returns a String representation of the todo task in the format required for saving into hard disk.
     *
     * @return String representation of the todo task in the format required to save into hard disk.
     */
    @Override
    public String formatData() {
        return String.format("T, %d, %s\n", getStatusInt(), getContent());
    }

    /**
     * Returns the String representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
