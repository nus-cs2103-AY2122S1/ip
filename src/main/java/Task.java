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
    public void CompleteTask() {
        done = true;
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
