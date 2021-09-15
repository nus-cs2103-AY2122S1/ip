package duke.tasks;

import duke.tasks.Task;

import java.time.format.DateTimeFormatter;

/**
 * Todo (Task). Can be added to list in Duke.
 * @author Ruth Poh
 */
public class Todo extends Task {

    /**
     * Initializes Todo.
     * @param taskString Task.
     */
    public Todo(String taskString) {
        super(taskString);
        super.date = null;
        super.time = null;
    }

    /**
     * Getter method for date Todo occurs at.
     * @return Date that Event occurs at in String form.
     */
    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Getter method for time Todo occurs
     * @return Time that Event occurs at in String form.
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Converts Todo to String for Storage.
     * @return Todo String for storage.
     */
    @Override
    public String toStorageString() {
        String isDoneString;
        if (super.isDone) {
            isDoneString = "1";
        } else {
            isDoneString = "0";
        }
        return ("T | " + isDoneString + " | " + super.taskString);
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
