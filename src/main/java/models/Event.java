package models;

import models.Task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {

    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event temp = (Event) obj;
            return temp.toString() == this.toString();
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[E][ ] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}