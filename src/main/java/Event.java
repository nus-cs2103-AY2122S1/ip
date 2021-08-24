import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String detail, LocalDateTime at) {
        super(detail,"E");
        this.at = at;
    }

    public LocalDateTime getTime() {
        return at;
    }

    private LocalDateTime parseTime(String time) {
        LocalDateTime d = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            d = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            Duke.echo("Invalid entry. Valid event format: event meeting /at 19/08/2021 23:59");
        }
        return d;
    }

    @Override
    public String toString() {
        String time = at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
