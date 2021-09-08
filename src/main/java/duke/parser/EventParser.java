package duke.parser;

import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.TaskParseException;
import duke.task.Event;
import duke.task.Task;
import duke.util.DukeDateTime;

/**
 * Parses events.
 */
public class EventParser {
    private static final int NUMBER_OF_FIELDS = 4;

    /**
     * Parses an <code>Event</code> from its text representation.
     *
     * @param text Text representation found.
     * @return Corresponding <code>Event</code> object.
     * @throws DukeException If the text representation cannot be parsed accurately.
     */
    protected static Event parse(String text) throws DukeException {
        String[] eventDetails = text.split(Pattern.quote(Task.FIELD_SEPARATOR), NUMBER_OF_FIELDS);
        if (eventDetails.length < NUMBER_OF_FIELDS) {
            throw new TaskParseException(text);
        }
        boolean isDone = eventDetails[1].equals(Task.TASK_COMPLETED_SYMBOL);
        String name = eventDetails[2];
        DukeDateTime timestamp = DukeDateTime.parseIso(eventDetails[3]);
        return new Event(name, isDone, timestamp);
    }
}
