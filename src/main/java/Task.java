public class Task {
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public String getTaskDesc() {
        return description;
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + description;
    }
}