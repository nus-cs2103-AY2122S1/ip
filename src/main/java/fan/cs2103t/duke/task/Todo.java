package fan.cs2103t.duke.task;

/**
 * Represents a task of type "todo".
 * <p>
 * This is a subclass of the <code>Task</code> class.
 * A task of type "todo" does not have a specific deadline or event period.
 */
public class Todo extends Task {

    /**
     * Constructs a task with the specified description and type "todo",
     * with an initial completion status "not done".
     *
     * @param description the description of the "todo" task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the description of this task with type "todo" and its completion status.
     *
     * @return the detailed description of this "todo" task.
     */
    @Override
    public String getDescriptionWithStatus() {
        return "[T]" + super.getDescriptionWithStatus();
    }

}
