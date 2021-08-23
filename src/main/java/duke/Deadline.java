package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;
    
    public Deadline(String desc, String by) throws DukeException {
        super(desc, "D");
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("duke.Deadline should be in the form yyyy-mm-dd" +
                    "\ni.e. deadline read book /by 2021-06-18"); 
        }
    }

    public String toData() {
        return super.toData() + "~S~" + by;
    }
    
    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
