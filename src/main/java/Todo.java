public class Todo extends Task {

    public Todo(String input) {
        super(input.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
