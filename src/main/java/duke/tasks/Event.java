package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

import java.time.DateTimeException;

public class Event extends Task {
    private DukeDateTime end = new DukeDateTime();

    /**
     * Instantiates a blank Event.
     */
    public Event() {
    }

    /**
     * Instantiates an Event based on a database entry.
     */
    public Event(String entry, boolean done) throws DukeException {
        try {
            String[] args = entry.split("( \\| )", 4);
            if (args.length < 4) {
                throw new DukeException("Error reading DB");
            }
            super.desc = args[0];
            super.done = done;
            super.dateTime = DukeDateTime.parse(args[1]);
            end = DukeDateTime.parse(args[2]);
            if (args.length == 4) {
                super.details = args[3];
            }
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
    }

    /**
     * Adds a start and end time to the event.
     *
     * @param rawArgs Argument of the format "START_DATE_TIME ~ END_DATE_TIME / ADDITIONAL_INFO".
     * @throws DukeException
     */
    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) {
            return;
        }
        try {
            String[] parts = args[0].split("~");
            dateTime = DukeDateTime.parse(parts[0].trim());
            if (parts.length > 1) {
                end = DukeDateTime.parse(parts[1].trim());
            }
            if (args.length > 1) {
                details = args[1];
            }
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }

    /**
     * Returns this event formatted as a database entry.
     *
     * @return String representing this event in database format.
     */
    public String toDatabaseFormat() {
        return String.format("E | %d | %s | %s | %s | %s", super.done ? 1 : 0, super.desc,
                dateTime.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.PRINT_TIME),
                end.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.TIME), details);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (obj instanceof Event) {
                Event that = (Event) obj;
                return end.equals(that.end);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String timeSeq = dateTime.format() + 
                (DukeDateTime.isEmpty(end) ? "" : " to " + end.format());
        String detailsAdd = (details.equals("") ? details : " -- " + details);
        return "[E]" + super.toString() + String.format(" (at: %s%s)", timeSeq, detailsAdd);
    }
}
