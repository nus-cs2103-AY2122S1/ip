package duke.task;

/**
 * ToDo class.
 * Used to represent a todo task.
 *
 * @author KelvinSoo
 * @version Level-8
 *
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
    public ToDo(String descripton, Boolean isDone) {
        super(descripton);
        if (isDone) {
            super.markAsDone();
        }
    }

    @Override
    public String getMetaData() {
        return String.format("T|%s", super.getMetaData());
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }
}
