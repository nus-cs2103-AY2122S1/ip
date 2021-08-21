package Tasks;

public class Task {
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

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X": " ", this.taskDetails);
    }
}
