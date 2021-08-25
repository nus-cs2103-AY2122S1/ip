package duke.task;

public abstract class Task {
    final private String action;
    private boolean isCompleted;

    public Task(String action) {
        this.action = action;
        this.isCompleted = false;
    }

    public String getAction() {
        return this.action;
    }

    public boolean isComplete() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    private String getStatusIcon() {
        return isCompleted ? "X" : " ";
    }


    abstract public String toSaveFormat() ;

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.action);
    }
}
