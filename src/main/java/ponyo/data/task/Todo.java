package ponyo.data.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // File operations
    @Override
    public String toStringInFile() {
        return "T - " + super.toStringInFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
