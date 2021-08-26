import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = null;
    }

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.by = null;
        this.byDate = byDate;
    }


    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)", 
                super.toString(), 
                byDate != null ? byDate.format(DATE_TIME_FORMAT) : by
        );
    }
}