package components;

public class Task {
    private String taskDescription;
    private boolean done;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUnDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[X] %s", this.taskDescription);
        } else {
            return String.format("[ ] %s", this.taskDescription);
        }
    }
}
