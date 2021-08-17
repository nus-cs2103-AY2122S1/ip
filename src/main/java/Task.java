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

    @Override
    public String toString() {
        return description;
    }

    public String finished() {
        this.isDone = true;
        String finishSentence = "[" + getStatusIcon() + "] " + this.description;
        return finishSentence;
    }
}
