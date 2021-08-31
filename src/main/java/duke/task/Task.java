package duke.task;

/**
 * Represents a task
 */
public class Task {

    public static final String DONE_MARKER = "X";
    public static final String NOT_DONE_MARKER = " ";

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /** Sets isDone to given boolean */
    public void setIsDone(boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        String marker;
        if (isDone) {
            marker = DONE_MARKER;
        } else {
            marker = NOT_DONE_MARKER;
        }
        return String.format("[%s] %s", marker, description);
    }
}