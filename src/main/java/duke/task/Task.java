package duke.task;

/**
 * Class to encapsulate a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor for Task Class specifying the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor for Task Class specifying the description and isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return a status icon based on if task isDone, "X" if done, " " if not done
     *
     * @return           the status icon based on isDone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return a completion binary based on if task isDone, "1" if done, "0" if not done
     *
     * @return           the completion binary based on isDone
     */
    public String getCompletionBinary() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    /**
     * Return the description of the task
     *
     * @return           the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as incomplete
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Change the description of the task
     */
    public void changeDescription(String description) {
        this.description = description;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return the string format for data source
     *
     * @return           string format of task for data source
     */
    public String toData() {
        return " | " + getCompletionBinary() + " | " + description;
    }
}
