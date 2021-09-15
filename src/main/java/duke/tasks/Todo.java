package duke.tasks;

import duke.tasks.Task;

import java.time.format.DateTimeFormatter;

/**
 * Todo (Task).
 * @author Ruth Poh
 */
public class Todo extends Task {

    /**
     * Initializes Todo.
     * @param taskString String representation of Todo Task.
     */
    public Todo(String taskString) {
        super(taskString);
        super.date = null;
        super.time = null;
    }

    /**
     * Returns blank string as Todo does not have a date.
     * @return Blank String
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns blank string as Todo does not have a time.
     * @return Blank String
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Returns blank string as Todo does not have a date or time.
     * @return Blank String
     */
    @Override
    public String getDateTimeStorage() {
        return "";
    }

    /**
     * Returns descriptive string representation of Todo.
     * @return In format '[T]: taskString'
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns string representation of Todo (Task) for storage.
     * @return In format 'T | isDoneStatus | taskString'
     */
    @Override
    public String toStorageString() {
        return ("T | " + this.getIsDoneBinary() + " | " + super.taskString);
    }


}
