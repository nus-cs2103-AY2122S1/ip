package uhtredragnarson.task;

/**
 * This is an abstract class in which the ToDo, Deadline and Event class will extend from.
 */
public abstract class Task {

    protected String title;
    protected boolean isDone;

    /**
     * Constructor for this class.
     *
     * @param title The description of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method returns X if the task is complete or an empty string otherwise.
     *
     * @return X or an empty string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
