public class Task {

    /** String containing the name of task */
    protected String description;
    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Constructor for Task. Takes in a String description.
     * Initialises description and isDone to false.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets an icon representation on whether task is done.
     *
     * @return An indicator for whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Completes a task and set its isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
