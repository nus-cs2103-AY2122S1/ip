import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task {
    protected String at;
    protected LocalDate date;

    Event(String content, String at) {
        super(content);
        this.at = at;
        try {
            date = LocalDate.parse(at);
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
            time = at;
        } else {
            time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}