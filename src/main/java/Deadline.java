import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String by;

    protected LocalDate date;
    private boolean hasDate;

    public Deadline(String description, String by) {
        super(description);
        try {
            date = LocalDate.parse(by);
            hasDate = true;
        } catch (DateTimeParseException e) {
            hasDate = false;
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                (hasDate ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by) + ")";
    }
}
