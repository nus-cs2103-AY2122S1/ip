package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[T]%s", stem);
    }
}