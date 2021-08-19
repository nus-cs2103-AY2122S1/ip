package Duke.Todo;

public class Todo {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    private String description;
    private boolean isDone;

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? DONE_ICON : NOT_DONE_ICON, this.description);
    }
}
