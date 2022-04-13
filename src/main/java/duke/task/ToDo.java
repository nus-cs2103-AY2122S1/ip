package duke.task;

/**
 * <h2>ToDo</h2>
 * A simple task which only has a name and a completion status.
 * For more complex tasks with a date and time see {@link duke.task.Deadline}, {@link duke.task.Event}.
 */
public class ToDo extends Task {

    /**
     * Creates a simple task which has only completion status. Default completion status is <code>false</code>.
     * @param taskName the name of the task.
     * @see Deadline
     * @see Event
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    private ToDo(ToDo oldTask) {
        super(oldTask);
    }

    /**
     * Factory method for task creation.
     * @param name name of the task.
     * @param isCompleted completion status of the task.
     * @return the newly created task.
     */
    public static ToDo createTask(String name, boolean isCompleted) {
        ToDo t = new ToDo(name);
        if (isCompleted) {
            return new ToDo(t);
        } else {
            return t;
        }
    }

    /**
     * {@inheritDoc}
     * @return a new <code>ToDo</code> which is exactly the same except with completion status set to <code>true</code>.
     */
    @Override
    public ToDo markAsCompleted() {
        return new ToDo(this);
    }

    @Override
    public String toString() {
        return "T: " + super.toString();
    }
}
