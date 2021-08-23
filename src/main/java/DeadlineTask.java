import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime time;
    static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");

    public DeadlineTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DATE_FORMAT) + ")";
    }
}