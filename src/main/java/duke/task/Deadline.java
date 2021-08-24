package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private final LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d,yyyy hh:mma", Locale.ENGLISH);

    public Deadline(String content, LocalDateTime by) {
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toStorageString() {
        String s1 = super.toStorageString();
        String s2 = String.format("D %s | %s", s1, by);
        return s2;
    }
}
