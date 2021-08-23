import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    Deadline(String content, String by) {
        super(content);
        this.by = by;
        try {
            date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        String time;
        if (date == null) {
            time = by;
        } else {
            time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}