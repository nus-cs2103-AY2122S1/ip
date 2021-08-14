public class Task {
    private boolean done;
    private String taskDetails;

    public Task(String taskDetails, boolean done) {
        this.done = done;
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

    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.taskDetails);
    }

}
