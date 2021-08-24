import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }
    
    public boolean getIsDone() {
        return this.isDone;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getTaskType() {
        return null;
    }
    
    public LocalDate getTiming() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}