public class Task {
    private boolean done;
    private String taskDetails;

    public Task() {
        this.done = false;
        this.taskDetails = "";
    }

    public Task(String taskDetails) {
        this.done = false;
        this.taskDetails = taskDetails;
    }

    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X]" + taskDetails;
        } else {
            return "[ ]" + taskDetails;
        }
    }
}
