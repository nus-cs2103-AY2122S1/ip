package duke.task;

public class TodoTask extends Task {
    
    public TodoTask(String taskCommand) {
        super(taskCommand);
    }

    public String saveString() {
        return String.format("T|%s", super.saveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
