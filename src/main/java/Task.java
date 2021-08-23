public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void finished() {
        this.done = true;
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[" + finished + "] " + this.taskName;
    }
}
