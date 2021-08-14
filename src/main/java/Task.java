public class Task {
    private String name;
    private boolean done;

    /**
     * Constructor for the Task class.
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
