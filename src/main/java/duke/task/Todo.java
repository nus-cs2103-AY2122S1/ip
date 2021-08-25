package duke.task;

public class Todo extends Task {
    private static final String TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getLabel() {
        return TYPE;
    }

    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString();
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getLabel(), getStatusIcon(), this.description);
    }
}
