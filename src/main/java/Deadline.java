import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalTime time;
    protected LocalDate date;

    public Deadline(String description, LocalTime time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}