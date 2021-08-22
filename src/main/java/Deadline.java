import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String dateString, String timeString) throws DukeException {
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
        return "D";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDateTimeString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("HHmm"))+ ")";
    }
}
