import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public String formatForSave() {
        return "D" + super.formatForSave() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
    }
}