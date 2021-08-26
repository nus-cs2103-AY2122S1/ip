import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String at;
    protected LocalDate atDate;


    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atDate = null;
    }

    public Event(String description, LocalDate atDate) {
        super(description);
        this.at = null;
        this.atDate = atDate;
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }


    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                atDate != null ? atDate.format(DATE_TIME_FORMAT) : at
        );
    }

    @Override
    public String toBackupFormat() {
        return String.format("E|%s|%s", super.toBackupFormat(), at);
    }
}
