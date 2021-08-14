public class Task {
    private boolean done;
    private String taskDetails;

    public Task(boolean done, String taskDetails) {
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
}
