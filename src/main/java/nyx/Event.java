package nyx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime at;

    public Event(String content, String at, boolean isDone) {
        super(content, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    public Event(String content, String at) {
        this(content, at, false);
    }

    @Override
    public String dataFormat() {
        String dateFormat =  at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
        return String.format("E, %d, %s, %s\n", isDoneInt(), getContent(), dateFormat);
    }

    @Override
    public String toString() {
        String dateFormat = at.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[E]" + super.toString() + " (at: " + dateFormat + ")";
    }
}