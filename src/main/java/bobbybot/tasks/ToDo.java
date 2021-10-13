package bobbybot.tasks;

/**
 * Represents a task without any time restrictions
 */
public class ToDo extends Task {

    /**
     *
     * @param description description of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor to specify if completed
     * @param isDone boolean flag if task is done
     * @param description description of task
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
