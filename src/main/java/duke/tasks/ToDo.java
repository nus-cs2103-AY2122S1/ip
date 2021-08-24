package duke.tasks;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description, Task.Type.T);
    }

    public ToDo(String description, boolean isDone) {
        super(Task.Type.T, isDone, description);
    }
}
