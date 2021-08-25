package tasks;

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

    // File operations
    public int getStatusNumber() {
        return (isDone ? 1 : 0); // mark done task with 1
    }

    // Sample: D - 0 - return book - June 6th
    public String toStringForFile() {
        return String.format("%d - %s", this.getStatusNumber(), this.description);
    }


    /**
     *
     * @return the String representation of a Deadline
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
