public class Todo {
    private final String text;

    public Todo(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
