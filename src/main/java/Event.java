import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String dateString, String timeString) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops! Make sure that your date and time is valid" +
                    " and is formatted as 'dd/MM/yyyy HHmm'.");
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("HHmm"))+ ")";
    }
}
