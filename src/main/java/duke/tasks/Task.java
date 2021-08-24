package duke.tasks;

/** Abstract class representing a task */
public abstract class Task {
    private boolean done = false;
    private String taskDetails;
    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public boolean markDone() {
        if (!this.done) {
            this.done = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of data stored in this task.
     *
     * @return String containing the task's data, which will be used for saving to disk.
     */
    public String toDataString() {
        return String.format("%d | %s", this.done ? 1 : 0, this.taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X": " ", this.taskDetails);
    }
}
