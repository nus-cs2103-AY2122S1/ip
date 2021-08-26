import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final LocalDateTime dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = convert(dateTime);
    }

    private LocalDateTime convert(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + ")";
    }
}
