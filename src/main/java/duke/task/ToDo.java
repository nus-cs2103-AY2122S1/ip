package duke.task;

/**
 * ToDo class.
 * Used to represent a todo task.
 * @author KelvinSoo
 * @version Level-8
 */
public class ToDo extends Task {
    /**
     * A constructor to create a new deadline task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A constructor to create a new deadline task.
     */
    public ToDo(String description, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
    }

    /**
     * A method to get data about the task in a savable format.
     * @return The savable data
     */
    @Override
    public String getMetaData() {
        return String.format("T|%s", super.getMetaData());
    }

    /**
     * A method to get the state of the task.
     * @return The status icon
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * A method to get all task details.
     * @return All the task details
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }
}
