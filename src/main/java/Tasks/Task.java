package Tasks;

import java.io.File;


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

    public String dataToString() {
        return String.format("%d | %s", this.done ? 1 : 0, this.taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X": " ", this.taskDetails);
    }
}
