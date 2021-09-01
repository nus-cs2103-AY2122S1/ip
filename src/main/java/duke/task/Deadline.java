package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class with time under task.
 */
public class Deadline extends Task {

    protected LocalDate time;

    /**
     * Constructor for Deadline.
     *
     * @param description Name of the deadline.
     * @param time Due time of the deadline.
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * String representation of a deadline.
     *
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        return ("D "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            );
    }

    /**
     * String format of a deadline that will be stored to the file.
     *
     * @return String to be stored.
     */
    @Override
    public String toStoredString() {
        return ("D "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
    }
}
