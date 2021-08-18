public class Task {
    private String description;
    private boolean done;
    public String type;

    /**
     * Private constructor for Task
     *
     * @param description is the string of the description of the given task
     */
    public Task(String description, String type) {
        this.description = description;
        this.done = false;
        this.type = type;
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
    @Override
    public String toString() {
        return this.description;
    }
}
