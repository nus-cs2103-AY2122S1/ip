package jarvis.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toStorageFormatString() {
        return String.format("%s;;;%s", "T", super.toStorageFormatString());
    }
}
