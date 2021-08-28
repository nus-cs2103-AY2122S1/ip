package pix.task;

public abstract class Task {
    protected String name;
    protected boolean done;

    /**
     * Constructor for the task and sets it to not done.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Constructor for the task.
     *
     * @param name Name of the task.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Sets the task to complete.
     */
    public void completeTask() {
        done = true;
    }

    /**
     * Shows whether the task is completed.
     *
     * @return Returns true if the task is complete and false otherwise.
     */
    public boolean isDone() {
        return done;
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
     * Gets the formatted saving name for the Pix.task.
     *
     * @return Returns the string to save for the Pix.task.
     */
    public abstract String getSaveName();

    /**
     * Gives the string representation of the task.
     * @return Returns the string representation of the task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
