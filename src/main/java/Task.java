public class Task {
    private String taskName;
    private boolean done;

    Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        done = true;
    }

    /**
     * Gets task name.
     * @return task name and whether it is completed.
     */
    public String getTaskName() {
        return "[" + (this.done ? "X" : " ") + "] " + this.taskName;
    }
}
