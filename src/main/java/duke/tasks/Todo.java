package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskIdentifier() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }
}
