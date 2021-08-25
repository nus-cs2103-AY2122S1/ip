import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            byDate = null;
        }
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String parseByDate() {
        return getByDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ((byDate == null) ? by : parseByDate()) + ")";
    }
}