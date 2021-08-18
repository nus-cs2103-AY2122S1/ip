public class Task {
    public String taskName;
    public boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void finished() {
        this.done = true;
    }
}
