package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Todo (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Todo extends Task {

    /**
     * Constructor to initialize Todo.
     *
     * @param taskstr Task.
     */
    public Todo(String taskstr) {
        super(taskstr);
        super.date = null;
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
