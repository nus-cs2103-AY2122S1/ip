package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    private String dateForObject;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.dateForObject = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * toString() method for deadline object
     *
     * @return String The task printed in the correct format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.by;
    }
}