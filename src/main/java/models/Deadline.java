package models;

import models.Task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D][ ] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}