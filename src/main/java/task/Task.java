package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String notes;

    public Task(String description, String notes, boolean completed) {
        this.description = description;
        this.isDone = completed;
        this.notes = notes;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }
    public void markIsDone() {
        this.isDone = true;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getNotes() {
        return this.notes;
    }

    public String showIfHaveNotes() {
        return getNotes().isEmpty()
                ? ""
                : "**" + this.notes + "**";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n%s\n", this.getStatusIcon(), this.description, showIfHaveNotes());

    }

    /**
     * Returns the type of task in String.
     *
     * @return string format of task type.
     */
    public abstract String getType();
    public abstract String getDeadline();
}
