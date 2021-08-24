package DukePakage;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor
     * @param description The string that describes the DukePakage.Task.
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
     * Return the status of whether the DukePakage.Task is done.
     * @return Boolean indicating if DukePakage.Task is done.
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
     * Return the description of the DukePakage.Task.
     * @return String of the DukePakage.Task description.
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
