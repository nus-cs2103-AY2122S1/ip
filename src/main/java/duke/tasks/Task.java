package duke.tasks;


public abstract class Task {

    private boolean done = false;
    private String taskDetails;

    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public boolean markDone() {
        if (!done) {
            done = true;
            return true;
        } else {
            return false;
        }
    }

    public String toDataString() {
        return String.format("%d | %s", done ? 1 : 0, taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X": " ", taskDetails);
    }
}
