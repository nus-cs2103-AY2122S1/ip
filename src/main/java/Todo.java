public class Todo {
    private final String text;
    private boolean isDone;

    public Todo(String text) {
        this.text = text;
        this.isDone = false;
    }

    public Todo markDone() {
        this.isDone = true;
        return this;
    }

    public String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}