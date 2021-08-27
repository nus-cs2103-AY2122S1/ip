package TiTi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        checkDate(at);
    }

    private void checkDate(String at) {
        if (at.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}