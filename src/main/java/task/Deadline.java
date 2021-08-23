package task;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private String deadline;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getLabel() {
        return TYPE;
    }

    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString() + " | "
                + deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getLabel(), getStatusIcon(), this.description, this.deadline);
    }
}
