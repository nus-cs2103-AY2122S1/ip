public class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark done task with X
    }

    public void markCompleted() {
        this.completed = true;
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
