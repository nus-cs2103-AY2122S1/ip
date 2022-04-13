package duke.task;

/**
 * Represents a task to do
 */
public class TodoTask extends Task {

    /**
     * Constructor for <code>TodoTask</code>
     *
     * @param content the content of the task
     * @param isDone true if the task is done, otherwise false
     */
    public TodoTask(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Returns the string representation of the object
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
