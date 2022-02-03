package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.at.format(displayFormatter) + ")");
    }

    /**
     * Returns the String representation of the Event as used in the storage.
     *
     * @return String representation of the Event in the storage format.
     */
    @Override
    public String toStorageString() {
        return ("E|" + super.getStatusNumber() + "|" + super.getDescription()
                + "|" + this.at.format(formatter));
    }

    @Override
    public String toUndoneString() {
        return ("[E]" + super.toUndoneString() + " (at: " + this.at.format(displayFormatter) + ")");
    }
}
