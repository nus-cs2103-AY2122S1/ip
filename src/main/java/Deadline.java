import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String content, String by, boolean isDone) {
        super(content, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    public Deadline(String content, String by) {
        this(content, by, false);
    }

    @Override
    public String dataFormat() {
        String dateFormat =  by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
        return String.format("D, %d, %s, %s\n", isDoneInt(), getContent(), dateFormat);
    }

    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }
}