public class Todo extends Task {
    public Todo(String input) {
        super(input.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
