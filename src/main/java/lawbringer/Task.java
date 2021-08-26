package lawbringer;

public abstract class Task {

    protected String title;
    protected boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
