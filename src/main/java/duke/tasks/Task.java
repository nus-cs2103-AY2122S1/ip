package duke.tasks;

import duke.item.Item;

/**
 * Represent a particular task.
 */
public abstract class Task extends Item {
    private String description;
    private boolean isDone;
    protected Integer order;

    /**
     * Creates a task object.
     *
     * @param description Description of what the task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.order = 0;
    }

    /**
     * Gets the description of the task.
     *
     * @return String that describes what the task is about.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the information of the task such as its description to store in the hard disk.
     *
     * @return String that contains the information.
     */
    public abstract String getInfo();

    /**
     * Gets the type of the task and denotes it with a letter to store in the hard disk.
     *
     * @return The letter that indicates the type of the task.
     */
    public abstract String getType();

    /**
     * Gets the string indicating whether the task has been completed or not to be stored in hard disk.
     *
     * @return String indicating whether task has been completed.
     */
    public String getDone() {
        return (isDone ? "1" : "0");
    };

    /**
     * Gets the icon indicating whether task has been completed to be printed to the user interface.
     *
     * @return Icon indicating whether task has been completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Mark the particular task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Stores the task in the hard disk.
     *
     * @return String denoting the Task which will be stored in the hard disk.
     */
    //public abstract String storeTask();

    public void setTaskCompletionStatus(String status) {
        if(status.equals("1")) {
            markAsDone();
        }
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task created.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
