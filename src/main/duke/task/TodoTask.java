package duke.task;

public class TodoTask extends Task {
    
    public TodoTask(String taskCommand) {
        super(taskCommand);
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String saveString() {
        return String.format("T|%s", super.saveString());
    }

    /**
     * Format task to be displayed in list.
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
