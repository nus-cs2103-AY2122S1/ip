import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate localDate;
    protected LocalTime localTime;

    public Deadlines(String description, LocalDate localDate, LocalTime localTime) {
        super(description);
        this.localDate = localDate;
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] " + this.description + "(by: %s %s)",
            this.getStatusIcon(),
            this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
            this.localTime.toString());
    }
}
