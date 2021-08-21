package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDate date;

    public Event(String details, String at, String date) {
        super(details);
        this.at = at;
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
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " )";
        }
    }
}
