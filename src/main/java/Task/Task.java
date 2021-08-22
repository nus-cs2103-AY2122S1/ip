package Task;

public class Task implements Comparable<Task> {

    private final String description;
    private boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return getDone() ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return description;
    }

    public String getTask() { return description; }

    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    @Override
    public int compareTo(Task o) {
        if (!this.isDone && o.isDone) {
            return -1;
        } else if (this.isDone && !o.isDone) {
            return 1;
        } else {
            return 0;
        }
    }
}
