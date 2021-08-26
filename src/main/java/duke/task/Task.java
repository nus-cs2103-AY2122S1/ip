package duke.task;

public abstract class Task {
    private boolean completed;

    public Task() {
        completed = false;
    }

    public void setCompleted() {
        completed = true;
    }

    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    public int isCompleted() {
        return completed ? 1 : 0;
    }

    public abstract String toStorageFormat();

}
