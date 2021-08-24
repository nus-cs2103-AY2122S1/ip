import java.time.LocalDate;

/**
 * Task contains information about a task.
 */
public abstract class Task {
    private String value = null;
    private boolean isDone = false;
    // takes in LocalDate
    private LocalDate time = null;
    public Task(String value){
        this.value = value;
        this.isDone = false;
    }

    public LocalDate getTime() {
        return time;
    }

    public abstract String getType();

    public boolean getDone() {
        return isDone;
    }

    /**
     * Getting the information of the task.
     * @return Information of the task.
     */
    public String getValue() {
        return value;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Get the status icon for this task.
     * @return A string representing the state of the task.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + value;
    }

}
