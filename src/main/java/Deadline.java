import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")");
    }
}
