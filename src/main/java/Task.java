/**
 * This class encapsulates a task that Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Instantiates a new task
     *
     * @param taskName description of the task.
     */
    public Task(String taskName) {
        this.taskName= taskName;
        this.isDone = false;
    }

    /** Marks task as done **/
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks the completion status of the task.
     *
     * @return true if task is complete, false otherwise,
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Gets the String representation of the task's status.
     *
     * @return "X" if completed and "" otherwise
     */
    private String getIcon() {
        return isDone ? "X" :"";
    }

    /**
     * String representation of a task
     *
     * @return string representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIcon(), taskName);
    }
}
