public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return String.format("T %s\b\b", super.toDataString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}