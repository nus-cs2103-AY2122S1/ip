public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[T][%c] %s", isDone ? 'X' : ' ', description);
    }
}
