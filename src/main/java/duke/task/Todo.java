package duke.task;

public class Todo extends Task{

    public Todo(String taskName, boolean status) {
        super(taskName, status);
    }

    public String displayTask() {
        return "T " + super.displayTask();
    }
}
