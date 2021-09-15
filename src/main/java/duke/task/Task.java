package duke.task;

/**
 * The class to represent a task.
 */
public abstract class Task {

    /** name of the task */
    protected String name;

    /** whether the task has been completed */
    protected boolean isDone;

    /**
     * Constructor of Task class
     *
     * @param name name of task
     */
    public Task(String name) {
        assert(name != null);
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor of Task class
     *
     * @param name name of task
     * @param isDone whether the task has been completed
     */
    public Task(String name, boolean isDone) {
        assert(name != null);
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Method to return the symbol indicating if the task has been completed
     *
     * @return symbol indicating if the task has been completed
     */
    public String getStatusIcon() {
        return (this.isDone ? "✔︎" : " ");
    }

    /**
     * Method to get the name of the task
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Method to return the string representation of the Task
     *
     * @return the string representation of the Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }

    /**
     * Method to return the string to be recorded of the Task instance
     *
     * @return the string to be recorded of the Task instance
     */
    public abstract String getRecordString();
}
