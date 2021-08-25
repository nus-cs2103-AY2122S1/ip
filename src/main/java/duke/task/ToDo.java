package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String taskToLine() {
        return "T" + super.taskToLine();
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[T]" + description;
    }
}
