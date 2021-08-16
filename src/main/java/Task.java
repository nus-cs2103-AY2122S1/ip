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

    public void markTaskDone() {
        this.isDone = true;
        printTaskStatus();
    }

    public void printTaskStatus() {
        System.out.println(" [" + this.getStatusIcon() + "] " + this.description);
    }

    //...
}
