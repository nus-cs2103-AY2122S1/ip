public class Todo extends Task{
    public Todo(String taskName, boolean doneStatus) {
        super(taskName, doneStatus);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
