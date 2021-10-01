package duke;

/** Class that handles writing and reading of the tasks */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the status icon of the task based on whether it is completed.
     * or not
     *
     * @return String Either "X" or " " based on the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     *
     * @return void
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the type of the task
     *
     * @return the type of task - either T for to-do, D for deadline
     * or E for event
     */
    public String getType() {
        return this.toString().substring(1,2);
    }
}