package duke.tasks;

/**
 * The Task class encapsulates a task with the task name
 * if the task is done or not.
 */
public class Task {

    private String name;
    private boolean isDone;

    /**
     * The constructor for the Task class.
     *
     * @param name Name of the task.
     * @param isDone If the task is done or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Gets the completion status of the task and returns a string
     * based on if it is complete or not.
     *
     * @return "X" to mark a task as complete and " " as not complete.
     */
    public String getDoneMarker() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Gets the completion status of the task and returns a string
     * based on if it is complete or not. This is to store the task
     * as a string for Storage.
     *
     * @return "1" to mark a task as complete and "0" as not complete.
     */
    public String getDone() {
        return isDone ? "1" : "0";
    }

    public String getMarker() {
        return " ";
    }

    public String getTime() {
        return " ";
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the completion status of the task as true.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns the string value of a task.
     *
     * @return The task in string.
     */
    @Override
    public String toString() {
        return "[" + this.getDoneMarker() + "] " + this.getName();
    }

}
