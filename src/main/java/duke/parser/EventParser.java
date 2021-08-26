package duke.parser;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.DukeDateTime;

public class EventParser {
    /**
     * Parses an <code>Event</code> from its text representation.
     * @param text the text representation found
     * @return the corresponding <code>Event</code> object
     * @throws DukeException if the text representation cannot be parsed accurately
     */
    protected static Event parse(String text) throws DukeException {
        String[] eventDetails = text.split(" \\| ", 4);
        if (eventDetails.length < 4) {
            throw new DukeException(String.format("Cannot parse Event from \n\t`%s`", text));
        }
        boolean isDone = eventDetails[1].equals("X");
        String name = eventDetails[2];
        DukeDateTime timestamp = DukeDateTime.parseIso(eventDetails[3]);
        return new Event(name, isDone, timestamp);
    }
}
