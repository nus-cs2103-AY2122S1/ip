import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String detail, LocalDateTime by) {
        super(detail, "D");
        this.by = by;
    }

    public LocalDateTime getTime() {
        return by;
    }

    private void parseTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            this.by = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            Duke.echo("Invalid entry. Valid deadline format: deadline do HW /by 19/08/2021 23:59");
        }
    }

    @Override
    public String toString() {
        String ddl = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
