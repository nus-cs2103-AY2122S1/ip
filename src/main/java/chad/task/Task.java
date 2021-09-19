package chad.task;

/**
 * Represents a user task.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Task {

    private static final String DONE_MARK = "X";
    private static final String DONE_INDICATOR_TEMPLATE = "[%s]";
    private static final String NOT_DONE_INDICATOR = "[ ]";
    private static final String TASK_STRING_REPRESENTATION_TEMPLATE = "%s%s %s";

    private String description;
    private boolean isDone;

    /**
     * Creates a task with the given task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.description = taskDescription;
        this.isDone = false;
    }

    /**
     * Gets the representation of the task for file storage.
     *
     * @return The representation of the task.
     */
    public abstract String getFileRepresentation();

    abstract String getTypeIndicator();

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    void unmarkDone() {
        isDone = false;
    }

    private String getDoneIndicator() {
        if (isDone) {
            return String.format(DONE_INDICATOR_TEMPLATE, DONE_MARK);
        } else {
            return NOT_DONE_INDICATOR;
        }
    }

    boolean isDone() {
        return isDone;
    }

    String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format(TASK_STRING_REPRESENTATION_TEMPLATE, getTypeIndicator(), getDoneIndicator(), description);
    }
}
