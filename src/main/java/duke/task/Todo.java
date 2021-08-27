package duke.task;

public class Todo extends Task{

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    public String displayTask() {
        return "T " + super.displayTask();
    }
}
