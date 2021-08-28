package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return String.join(" | ", "T", this.isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
