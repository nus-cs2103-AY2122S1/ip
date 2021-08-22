import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    // for comparing deadline objects by description
    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        date = by.toLocalDate();
        time = by.toLocalTime();
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        date = by;
        time = null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day" :
                time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH))) + ")";
    }
}