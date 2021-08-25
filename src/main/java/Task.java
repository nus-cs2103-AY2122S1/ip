public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void taskDone() {
        this.isDone = true;
    }
    
    public String saveText() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " / " + this.description;
    }

    @Override
    public String toString() {
        String taskStr = String.format("[%s] ", getStatusIcon())
                + this.description;
        return taskStr;
    }
}
