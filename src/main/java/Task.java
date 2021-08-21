public abstract class Task {
    protected String name;
    protected boolean done;

    /**
     * Constructor for the Task.
     * @param name Name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Sets the Task to complete.
     */
    public void completeTask() {
        done = true;
    }

    /**
     * Shows whether the task is completed.
     * @return True if the task is complete and false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Getter for the task's name.
     * @return Returns the task's name.
     */
    public String getTaskName() {
        return name;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
