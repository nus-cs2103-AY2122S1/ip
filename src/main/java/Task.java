public class Task {
    /** A String that stores the description of the task. **/
    protected String description;

    /** A boolean that indicates whether the task is done. **/
    protected boolean isDone;

    /**
     * A public constructor to initialize the task.
     *
     * @param description The given description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A public method to generate the status icon of the task.
     *
     * @return A String, the status icon.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * A public method to set the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A override toString() method.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
