package brobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d,yyyy hh:mma",
            Locale.ENGLISH);

    /**
     * Constructor.
     *
     * @param content Main content of the Deadline Task.
     * @param by      Date of deadline.
     */
    public Deadline(String content, LocalDateTime by) {
        super(content);
        this.by = by;
    }

    /**
     * String representation of a deadline.
     *
     * @return Deadline in string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * String representation of a Deadline task for brobot.storage.
     *
     * @return Deadline task in String(Storage format).
     */
    public String toStorageString() {
        String basicStorageFormat = super.toStorageString();
        return String.format("D %s | %s", basicStorageFormat, by);
    }
}
