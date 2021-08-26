package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean contains(String content) {
        return description.contains(content);
    }
    
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
    
    public String toStorage() {
        return (this.isDone + "%" + this.description + "\n");
    }
}
