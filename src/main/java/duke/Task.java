package duke;

/**
 * Task class used by the duke class
 * Stores a description of task and whether it is done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status on the task and returns appropriate icon
     *
     * @return String "X" if task is done & " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markUndone() { this.isDone = false; }

    /**
     * Changes description of task
     *
     * @param description to change into
     */
    public void changeDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}