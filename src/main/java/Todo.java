public class Todo extends Task {

    private final String identifier = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        return result;
    }

    @Override
    public String databaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription();
        return result;
    }
}
