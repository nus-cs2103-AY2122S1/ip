package duke.task;

/**
 * Represents a Task object. It is an abstract class.
 */
public abstract class Task {
    private boolean isDone = false;
    private boolean hasNotes = false;
    private String notes = "";

    public abstract String showFullDescription();
    public abstract String showTaskName();
    public abstract String showType();
    public abstract String showWhen();

    /**
     * Returns an indication of whether the task is done or not.
     *
     * @return "[X]" indicating the task is done and "[ ]" if not done
     */
    public String checkDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public String checkNotes() {
        return hasNotes ? "[N]" : "[ ]";
    }

    public void writeNotes(String notes) {
        this.notes = notes;
    }

    public String showNotes() {
        return this.notes;
    }

    /**
     * Sets the done status of task to true
     */
    public void isDone() {
        this.isDone = true;
    }

    public void hasNotes() {
        this.hasNotes = true;
    }
}
