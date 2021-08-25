/**
 * Represents a ToDo, which is the simplest of Tasks.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gives the type of task.
     *
     * @return T for ToDo
     */
    @Override
    public String type() {
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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
