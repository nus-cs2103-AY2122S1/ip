public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName= taskName;
        this.isDone = false;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    public boolean isComplete() {
        return isDone;
    }

    private String getIcon() {
        return isDone ? "X" :"";
    }

    @Override
    public String toString() {
        return String.format("[ %s ] %s", this.getIcon(), taskName);
    }
}
