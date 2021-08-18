public class Todo extends Task {
    private static final String type = "T";

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getLabel() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getLabel(), getStatusIcon(), this.description);
    }
}
