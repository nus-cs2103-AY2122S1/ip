import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String input) {
        super(input.split(" /")[0].substring(9));
        this.by = LocalDate.parse(input.split(" /")[1].substring(3));
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String getTime() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}