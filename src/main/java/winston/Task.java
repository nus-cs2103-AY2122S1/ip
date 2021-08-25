package winston;

public class Task {
    private String description;
    private boolean done;

    /**
     * Private constructor for Task
     *
     * @param description is the string of the description of the given task
     */
    public Task(String description, String type, boolean isCompleted) {
        this.description = description;
        this.done = isCompleted;
    }

    public void setComplete() {
        this.done = true;
    }

    public String taskCompletion() {
        if (done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    protected String saveFormat() {
        if (done) {
            return 1 + "," + this.description;
        } else {
            return 0 + "," + this.description;
        }
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
