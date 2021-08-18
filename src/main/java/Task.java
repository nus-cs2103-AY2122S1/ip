public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
        System.out.println("    " + this);
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}