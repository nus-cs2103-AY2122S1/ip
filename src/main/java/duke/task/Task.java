package duke.task;

/**
 * Class that handles all types of tasks.
 */
public class Task {
    private boolean isDone;
    private String detail;
    private String type;

    /**
     * Constructor to initialize task.
     *
     * @param detail Description of task.
     * @param type The type of task: Deadline, Event or todo.
     */
    public Task(String detail, String type) {
        this.detail = detail;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks a task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Checks if a task is completed.
     *
     * @return A boolean true if task is completed, and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Return a tag of the type of Task.
     *
     * @return String representation of the type of Task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a description of the Task.
     *
     * @return String representation of the details of the Task.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return type + (isDone ? "[✓] " : "[✘] ") + this.detail;
    }
}
