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

    public boolean markAsDone() {
        // returns true if task is marked as done, returns false if task is already done
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

}
