public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    public String fileSaveFormat() {
        return String.format("T | %d | %s", this.isDone() ? 1 : 0, this.taskName());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
