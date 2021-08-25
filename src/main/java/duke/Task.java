package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }   
    
    public boolean isDone() {
        return this.isDone;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    public String getDescription() {
        return this.description;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public String toString() {
        return "|" + this.getStatusIcon() + "| " + this.description;
    }
}