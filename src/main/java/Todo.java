public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String taskListOnDisk() {
        return "Todo" + super.taskListOnDisk();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}