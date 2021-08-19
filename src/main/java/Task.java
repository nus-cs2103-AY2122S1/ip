public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A Method to mark the task as completed
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * A method to get the status icon
     * @return the symbol that shows if an event is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
