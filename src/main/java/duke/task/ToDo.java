package duke.task;

/**
 * Represents a ToDo, which is the simplest of Tasks.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo, which takes in a task name.
     *
     * @param taskName name of task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gives the type of task.
     *
     * @return T for ToDo
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo();
    }

    /**
     * Overriden toString method.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
