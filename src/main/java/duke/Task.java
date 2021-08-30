package duke;

/**
 * Encapsulates the supertype of Deadlines, Events and Todos which is Task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor
     * @param description The string that describes the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Correctly assigns the task a string that indicates if it has been marked as done or not.
     * @return String that hows the correct icon.
     */
    public String getStatusIcon() {
        if (!isDone) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }

    /**
     * Return the status of whether the Task is done.
     * @return Boolean indicating if Task is done.
     */
    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Allows the marking of Tasks as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Return the description of the Task.
     * @return String of the Task description.
     */
    public String getDescription() {
        return this.description;
    }

    public abstract String toTxt();

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
