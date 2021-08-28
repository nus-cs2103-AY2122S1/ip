public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String taskList() {
        return "Todo " + super.taskList();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}