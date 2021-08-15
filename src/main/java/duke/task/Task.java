package duke.task;

/**
 * Represents a user task.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Task {

    private static String DONE_MARK = "X";

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
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
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

    abstract String getTypeIndicator();

    private String getDoneIndicator() {
        if (isDone) {
            return String.format("[%s]", DONE_MARK);
        } else {
            return "[ ]";
        }
    }
}
