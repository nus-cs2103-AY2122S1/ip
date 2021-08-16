public abstract class Task {
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
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTypeIcon(), this.getStatusIcon(), this.description);
    }

    /**
     * A public abstract method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public abstract String getTypeIcon();
}
