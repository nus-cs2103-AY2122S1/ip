package cs2103.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline Task object, which inherits from the Task class.
 */
public class Deadline extends Task {
    protected int index;
    protected String by;
    protected LocalDate date;

    public Deadline(int index, String description, String by) {
        super(index, description);
        this.index = index;
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("yyyy MM dd")) + ")";
    }
}