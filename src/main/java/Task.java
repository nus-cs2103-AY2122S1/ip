public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
    
    public String toBackupFormat() {
        return String.format("%s | %d", description, isDone ? 1 : 0);
    }
}