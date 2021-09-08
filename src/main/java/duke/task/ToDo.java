package duke.task;

/**
 * A To-do kind of Task.
 * @author Thomas Hogben
 */
public class ToDo extends Task {

    /**
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @param description The description of the Task.
     * @param isDone Whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return The save string of this task.
     */
    @Override
    public String getSave() {
        return "T" + super.getSave();
    }

    /**
     * @return The display string of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
