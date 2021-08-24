package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that must be done by a specified day and time.
 */
public class Deadline extends Task {

    private LocalDate by;
    private String time;

    public Deadline(String name, LocalDate by, String time) {
        super(name);
        this.by = by;
        this.time = time;
    }

    /**
     * Gets the date the task must be completed by.
     * @return the date the task must be completed by
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Gets the time the task must be completed by.
     * @return the string representing the time the task must be completed by
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Returns the string representation of the Deadline object.
     * @return the string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.getTime());
    }

    /**
     * This returns the string format the task is represented in the duke file.
     * @return the string representing a Deadline in the duke file
     */
    @Override
    public String print() {
        return String.format("D,%d,%s,%s %s", isCompleted() ? 1 : 0, this.getName(), this.getBy(), this.getTime());
    }
}
