package duke.task;

import duke.util.DukeDateTime;
import duke.exception.DukeException;

public class Event extends Task implements Timestampable {
    private final DukeDateTime timestamp;

    public Event(String name, DukeDateTime timestamp) {
        super(name);
        this.timestamp = timestamp;
    }

    public Event(String name, boolean isDone, DukeDateTime timestamp) {
        super(name, isDone);
        this.timestamp = timestamp;
    }

    public static Event fromText(String text) throws DukeException {
        String[] eventDetails = text.split(" \\| ", 4);
        if (eventDetails.length < 4) {
            throw new DukeException(String.format("Cannot parse Event from \n\t`%s`", text));
        }
        boolean isDone = eventDetails[1].equals("X");
        String name = eventDetails[2];
        DukeDateTime timestamp = DukeDateTime.parseISO(eventDetails[3]);
        return new Event(name, isDone, timestamp);
    }

    @Override
    public String toText() {
        String[] props = new String[]{"E", super.getStatusIcon(), super.getName(), this.timestamp.toISO()};
        return String.join(" | ", props);
    }

    @Override
    public boolean onSameDayAs(DukeDateTime date) {
        return DukeDateTime.onSameDay(this.timestamp, date);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timestamp);
    }
}
