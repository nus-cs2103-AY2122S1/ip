package duke;

/**
 * Encapsulates the abstract task class that concrete task implementations extend from.
 */
public abstract class Task {
    private final String description;
    private Boolean completionStatus;

    public Task(String description, Boolean completionStatus) {
        this.description = description;
        this.completionStatus = completionStatus;
    }

    public void completeTask() {
        completionStatus = true;
    }

    /**
     * Returns a String icon depending on whether the task is complete or not.
     *
     * @return The String icon.
     */
    public String completionIcon() {
        return completionStatus ? "[X]" : "[-]";
    }

    /**
     * Returns a String icon depending on what type the task is.
     *
     * @return The String icon.
     */
    public abstract String typeIcon();

    @Override
    public String toString() {
        return typeIcon() + " " + completionIcon() + " " + description;
    }
}
