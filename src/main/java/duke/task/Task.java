package duke.task;

/**
 * Represents a user task.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Task {

    private static final String DONE_MARK = "X";

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
    public abstract String getRepresentation();

    abstract String getTypeIndicator();

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    private String getDoneIndicator() {
        if (isDone) {
            return String.format("[%s]", DONE_MARK);
        } else {
            return "[ ]";
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
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeIndicator());
        sb.append(getDoneIndicator());
        sb.append(" ");
        sb.append(description);
        return sb.toString();
    }
}
