public class Todo extends Task {

    private final String identifier = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String result = identifier;
        result += super.toString();
        return result;
    }
}
