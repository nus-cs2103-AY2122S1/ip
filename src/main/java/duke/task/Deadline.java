package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by.format(displayFormatter) + ")");
    }

    /**
     * Returns the String representation of the Deadline as used in the storage.
     *
     * @return String representation of the Deadline in the storage format.
     */
    @Override
    public String toStorageString() {
        return ("D|" + super.getStatusNumber() + "|" + super.getDescription()
                + "|" + this.by.format(formatter));
    }

    @Override
    public String toUndoneString() {
        return ("[D]" + super.toUndoneString() + " (by: " + this.by.format(displayFormatter) + ")");
    }
}
