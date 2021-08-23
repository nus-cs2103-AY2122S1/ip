import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String content, String by) {
        super(content);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }
}