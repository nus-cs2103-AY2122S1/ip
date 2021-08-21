package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String by;
    private LocalDate date;

    public Deadline(String details, String by, String date) {
        super(details);
        this.by = by;
        this.date = date == null
                ? null
                : LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " )";
        }
    }
}
