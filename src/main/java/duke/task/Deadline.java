package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline type Task objects.
 */

public class Deadline extends Task{
    private final LocalDate date;

    /**
     * Basic constructor for Deadline Task objects.
     * @param label description of the Task.
     * @param date date associated with the Task.
     */
    public Deadline(String label, LocalDate date) {
        this.date = date;
        this.label = label;
    }

    /**
     * Getter for type.
     * @return "D" as an identifier for other parts of the application.
     */
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getDate() {
        return date.toString();
    }
}
