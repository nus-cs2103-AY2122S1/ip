public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        this(name, false);
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    protected Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean checkTaskDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String toText();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
