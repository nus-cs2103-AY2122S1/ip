package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    @JsonProperty
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
     * Required for JackSon.
     */
    private Deadline() {}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
