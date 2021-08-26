package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dateTimeBy;
    protected String by;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTimeBy = LocalDateTime.parse(by, formatter);
    }

    public Deadline(String num, String description, String by) {
        this(description, by);
        this.isDone = !num.equals("0");
        this.dateTimeBy = LocalDateTime.parse(by, formatter);
    }

    public String getFormattedBy() {
        return this.dateTimeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    @Override
    public String getFileString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }
}