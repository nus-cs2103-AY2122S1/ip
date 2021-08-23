import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    public Deadline(String isDone, String description, String by) {
        super(description, isDone.equals("1"));
        this.byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + byDate;
    }
}