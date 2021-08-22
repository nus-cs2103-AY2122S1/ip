public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toDukeStoreFormat() {
        return String.format("T | %s", super.toDukeStoreFormat());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
