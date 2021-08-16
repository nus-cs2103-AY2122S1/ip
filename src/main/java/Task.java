public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        String statusIcon = this.isDone ? "[X]" : "[ ]"; // mark done task with X
        return statusIcon + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }

}
