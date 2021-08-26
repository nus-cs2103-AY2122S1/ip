import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}