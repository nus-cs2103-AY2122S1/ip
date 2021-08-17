public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }
    @Override
    public String toString() {
        return super.toString(); // No preposition
    }
}
