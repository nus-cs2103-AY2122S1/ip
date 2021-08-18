public class Task {
    protected String taskstr;
    protected boolean isDone;

    public Task(String taskstr) {
        this.taskstr = taskstr;
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskstr;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

}
