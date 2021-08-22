package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to create a new Deadline object.
     * @param description the description of the Deadline Task.
     * @param by due date of Deadline Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the parameter by as a string.
     * @return by property as a String.
     */
    public String getBy() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
