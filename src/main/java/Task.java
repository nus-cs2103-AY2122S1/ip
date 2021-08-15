public class Task {

    private String details;
    private boolean completed;

    public Task(String details) {
        this.details = details;
        this.completed = false;
    }

    // Completes task
    public void complete() {
        this.completed = true;
    }

    // Check if task is completed
    public boolean isCompleted() {
        return this.completed;
    }

    // Return task details
    public String getDetails() {
        return details;
    }
}
