package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringData() {
        return "T" + super.toStringData();
    }

    @Override
    public boolean matches(String query) {
        return super.matches(query) || query.equalsIgnoreCase("todo");
    }
}
