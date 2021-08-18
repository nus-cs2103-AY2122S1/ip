public class Deadline extends Task {
    private static final String type = "D";
    private String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getLabel() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getLabel(), getStatusIcon(), this.description, this.due);
    }
}
