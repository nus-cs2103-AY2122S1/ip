public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    public static Todo fromInput(String input) throws Exception {
        String todoDescription = input.trim();
        if (todoDescription.length() == 0) {
            throw new Exception("Todo must have description");
        }
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
