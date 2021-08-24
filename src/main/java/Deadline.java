import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate dt;

    public Deadline(String description, LocalDate dt) {
        super(description);
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}