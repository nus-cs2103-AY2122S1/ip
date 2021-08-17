//Imported Partial Solution
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

    //Completes the Task
    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }
}
