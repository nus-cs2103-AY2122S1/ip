package duke.task;

public class Todo extends Task{
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
