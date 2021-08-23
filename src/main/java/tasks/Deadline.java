package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String time) {
        super(description);
        this.by = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedTime() +")";
    }
}
