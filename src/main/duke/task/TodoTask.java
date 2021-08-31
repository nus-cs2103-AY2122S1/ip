package duke.task;

public class TodoTask extends Task {
    
    public TodoTask(String taskCommand) {
        super(taskCommand);
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("T|%s", super.toSaveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
