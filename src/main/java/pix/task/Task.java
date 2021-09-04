package pix.task;

import java.util.Objects;

/**
 * Abstract class that all tasks inherit from.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for the task and sets it to not done.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for the task.
     *
     * @param name Name of the task.
     */
    public Task(String name, boolean isDone) {
        assert !Objects.equals(name, "") : "The task does not have a name!";
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the task to complete.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Sets the task to incomplete.
     */
    public void uncompleteTask() {
        isDone = false;
    }

    /**
     * Shows whether the task is completed.
     *
     * @return Returns true if the task is complete and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Getter for the task's name.
     *
     * @return Returns the task's name.
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Gets the formatted saving name for the task.
     *
     * @return Returns the string to save for the task.
     */
    public abstract String getSaveName();

    /**
     * Gives the string representation of the task.
     *
     * @return Returns the string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
