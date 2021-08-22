public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task (String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String taskName() {
        return this.taskName;
    }

    public abstract String fileSaveFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ",taskName);
    }

}
