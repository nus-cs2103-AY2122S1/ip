package dino.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dino.exception.*;

public class Event extends Task{

    private LocalDate at;

    public Event(String description, LocalDate at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
