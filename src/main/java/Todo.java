public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toSaveData() {
        return "T|" + super.toSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
