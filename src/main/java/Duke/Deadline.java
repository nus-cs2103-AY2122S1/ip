package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public String addToFile() {
        return "D | 0 | " + this.description + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}