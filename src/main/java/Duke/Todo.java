package duke;

public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    Todo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    @Override
    public String toFile() {
        return String.format("T | %s", super.toFile());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
