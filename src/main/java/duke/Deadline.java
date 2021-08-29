package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        try{
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            this.deadline = null;
            System.out.println("Deadline was not readable, saving as unspecified deadline");
        }

    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[D]%s (by: %s)", stem, this.deadline == null
                                                     ? "unspecified"
                                                     : this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}