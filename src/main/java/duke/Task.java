package duke;

public abstract class Task {

    protected static final String ERROR_ALREADY_DONE = "Error! The task has already been marked as complete!";
    protected static final String SUCCESS_DONE = "Nice! I've marked this task as done:";
    protected static final String INDENTATION_5 = "     ";
    protected static final String DIVIDER = " | ";

    protected String description;
    protected boolean done;

    /**
     * Marks the task as done, and returns the done message.
     * If already done, it'll return an error message.
     *
     * @return
     */
    public String setTaskAsDone() {
        if (this.done) {
            return Task.ERROR_ALREADY_DONE;
        } else {
            this.done = true;
            return Task.SUCCESS_DONE + "\n" + INDENTATION_5 + this.toString();
        }
    }

    abstract String toWriteString();

    public String getDescription() {
        return this.description;
    }
}
