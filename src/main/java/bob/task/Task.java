package bob.task;

public class Task {
    /** Description of user Task */
    private String description;

    /** Whether the Task has been completed by the user */
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
