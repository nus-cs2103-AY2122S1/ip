package duke.task;

/**
 * Exists to provide a parent class to Event, Deadline, and ToDo classes.
 *
 * @author Leong Hong Fai
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Creates new Task object.
     *
     * @param name Name of Task object.
     */
    public Task(String name) {
        assert !(name == null);
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks current task as completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setUncompleted() {
        this.isCompleted = false;
    }


    /**
     * Indicates task object's completion using an 'X' or a blank space ' '.
     *
     * @return X or space depending on completion status of the Task object.
     */
    public String getStatusIcon() {
        return (this.isCompleted
                ? "X"
                : " ");
    }


    /**
     * Represents Task in a String format
     *
     * @return A string consisting of the information of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }
}
