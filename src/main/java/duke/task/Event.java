package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    final private LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d,yyyy hh:mma", Locale.ENGLISH);


    public Event(String content, LocalDateTime at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    public String toStorageString() {
        String s1 = super.toStorageString();
        String s2 = String.format("E %s | %s", s1, at);
        return s2;
    }
}
