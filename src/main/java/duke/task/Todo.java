package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return String.format("TODO %s", super.toDataString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}