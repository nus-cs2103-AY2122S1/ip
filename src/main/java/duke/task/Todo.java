package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringData() {
        return "T | " + super.toStringData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
