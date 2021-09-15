package duke.task;

import duke.util.DukeDateTime;

/**
 * Represents a <code>Task</code> that occurs on a specific date and time.
 */
public class Event extends Task implements Timestampable {
    public static final char TEXT_ENCODING_CHAR = 'E';

    private final DukeDateTime timestamp;

    /**
     * Constructor for an Event object.
     */
    public Event(String name, DukeDateTime timestamp) {
        super(name);
        this.timestamp = timestamp;
    }

    /**
     * Constructor for an Event object with completion status.
     */
    public Event(String name, boolean isDone, DukeDateTime timestamp) {
        super(name, isDone);
        this.timestamp = timestamp;
    }

    @Override
    public String toText() {
        String[] props = {String.valueOf(TEXT_ENCODING_CHAR), super.getStatusIcon(), super.getName(),
                timestamp.toIso()};
        return String.join(FIELD_SEPARATOR, props);
    }

    @Override
    public boolean onSameDayAs(DukeDateTime date) {
        return DukeDateTime.onSameDay(timestamp, date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TEXT_ENCODING_CHAR, super.toString(), timestamp);
    }
}
