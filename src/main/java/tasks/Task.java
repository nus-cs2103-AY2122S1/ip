package tasks;

public class Task {
    public static String delimiter = " | ";
    protected String taskIcon;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String saveFormat() {
        return String.join(Task.delimiter,
                        this.taskIcon,
                        isDone ? "1" : "0",
                        this.description);
    }
}