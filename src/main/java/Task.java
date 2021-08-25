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

    public void markAsDone() {
        isDone = true;
    }

    public void setDone(int done) {
        if (done == 1) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public String toFileFormat() {
        return String.format("%d / %s", isDone ? 1 : 0, this.description);
    }
}
