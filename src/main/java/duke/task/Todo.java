package duke.task;


public class Todo extends Task {
    protected String taskType = "[T]";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return taskType + super.toString();
    }

}
