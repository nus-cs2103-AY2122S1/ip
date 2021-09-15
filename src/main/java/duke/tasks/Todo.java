package duke.tasks;

/**
 * Todo (Task)
 * @author Ruth Poh
 */
public class Todo extends Task {

    /**
     * Creates a Deadline Task with a specified todo.
     * @param taskString String representation of the todo
     */
    public Todo(String taskString) {
        super(taskString);
        super.date = null;
        super.time = null;
    }

    /**
     * Returns blank string as todo does not have a date.
     * @return Blank String
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns blank string as todo does not have a date or time.
     * @return Blank String
     */
    @Override
    public String getDateTimeStorage() {
        return "";
    }

    /**
     * Returns string representation of todo for storage.
     * @return In format 'T | isDoneStatus | taskString'
     */
    @Override
    public String toStorageString() {
        return ("T | " + this.getIsDoneBinary() + " | " + super.taskString);
    }

    /**
     * Returns descriptive string representation of todo.
     * @return In format '[T]: taskString'
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
