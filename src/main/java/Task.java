public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    // getter
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // setter
    public void markAsDone() {
        isDone = true;
    }

    public abstract String toStringInStorage();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
