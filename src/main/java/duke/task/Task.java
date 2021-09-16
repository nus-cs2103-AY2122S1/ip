package duke.task;

public abstract class Task {

    protected static final String ERROR_ALREADY_DONE = "Error! The task has already been marked as complete!";
    protected static final String SUCCESS_DONE = "Nice! I've marked this task as isDone:";
    protected static final String INDENTATION_5 = "     ";
    protected static final String DIVIDER = " | ";

    protected String description;
    protected boolean isDone;

    /**
     * Marks the task as isDone, and returns the isDone message.
     * If already isDone, it'll return an error message.
     *
     * @return
     */
    public String setTaskAsDone() {
        if (this.isDone) {
            return Task.ERROR_ALREADY_DONE;
        } else {
            this.isDone = true;
            return Task.SUCCESS_DONE + "\n" + INDENTATION_5 + this.toString();
        }
    }

    public abstract String toWriteString();

    public String getDescription() {
        return this.description;
    }
}
