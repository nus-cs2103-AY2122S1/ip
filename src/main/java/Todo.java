public class Todo extends Task{
    taskType type;
    public Todo(String taskName) {
        super(taskName);
        type = taskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
