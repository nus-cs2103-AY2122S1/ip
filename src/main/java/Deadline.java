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
    
    public Deadline(boolean isDone, String description, String by, LocalDate byDate) {
        super(isDone, description);
        this.by = by;
        this.byDate = byDate;
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

    @Override
    public String toBackupFormat() {
        return String.format(
                "D | %s | %s | %s | ", 
                super.toBackupFormat(),
                by == null ? "" : by,
                byDate == null ? "" : byDate.format(DATE_TIME_FORMAT)
        );
    }
}