package dino.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dino.exception.*;

public class Deadline extends Task {

    private LocalDate byDate;

    public Deadline(String description, String at) throws InvalidFormatException {
        super(description);
        try {
            this.byDate = LocalDate.parse(at);
        } catch (DateTimeParseException e){
            throw new InvalidFormatException("make sure the date", "yy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "D" + super.toString() + " | "
                + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
