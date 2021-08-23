import java.time.LocalDate;

class Task {
    protected String content;
    protected boolean isDone;
    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X":" ", this.content);
    }
}
