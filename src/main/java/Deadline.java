import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    @Override
    public String getTypeIndicator() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+ ")";
    }
}