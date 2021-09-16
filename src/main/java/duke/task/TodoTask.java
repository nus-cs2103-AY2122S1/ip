package duke.task;

public class TodoTask extends Task {

    /**
     * Create a new Todo Task
     * @param taskCommand User command
     */
    public TodoTask(String taskCommand) {
        super(taskCommand);
        assert(taskCommand.isEmpty());
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("T|%s", super.toSaveString());
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
