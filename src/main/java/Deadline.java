import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    // protected String ddlByTime;
    protected LocalDateTime ddlDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd/MMM/yyyy hh:mm a");

    public Deadline(String deadlineName, LocalDateTime byTime) {
        super(deadlineName);
        this.ddlDateTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(ddlDateTime) + ")";
    }
}
