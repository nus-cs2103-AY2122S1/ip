public abstract class Task {
    public String taskName;
    public boolean state;

    public Task(String taskName) {
        this.taskName = taskName;
        this.state = false;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public abstract String getTaskName();

    public abstract String getSymbol();

    @Override
    public String toString() {
        return (getState() ? "[X] " : "[ ] ") + taskName;
    }
}
