public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    protected Task markAsDone() {
        isDone = true;
        return this;
    }

    public boolean checkTaskDone() {
        return isDone;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
