package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Todo (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh
 */
public class Todo extends Task {

    /**
     * Constructor to initialize Todo.
     *
     * @param taskString Task.
     */
    public Todo(String taskString) {
        super(taskString);
        super.date = null;
        super.time = null;
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
