package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return by;
    }

    public String formatSave() {
        if (time != null) {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() +  " | "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + time;
        } else if (date != null) {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() + " | "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() + " | " + getTime();
        }
    }

    @Override
    public String toString() {
        if (time != null) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern(
                    "MMM dd yyyy")) + ", " + time + ")";
        } else if (date != null) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern(
                    "MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}