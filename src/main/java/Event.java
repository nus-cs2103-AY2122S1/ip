public class Event extends Task {
    private static final String type = "E";
    private String due;

    public Event(String description, String due) {
        super(description);
        this.due = due;
    }
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getLabel() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getLabel(), getStatusIcon(), this.description, this.due);
    }
}
