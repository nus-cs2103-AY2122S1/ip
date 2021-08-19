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

    public String getTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void done() {
        this.isDone = true;
    }

    public String markDone() {
        done();
        return "Nice! I've marked this task as done:\n" + getTask();
    }

    public String delete() {
        return getTask();
    }
}




















