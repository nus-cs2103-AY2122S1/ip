package duke.task;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;

/**
 * Represents an duke.tasks.Event object.
 */
public class Event extends DatedTask {
    /**
     * duke.tasks.Event constructor.
     *
     * @param description the event's description
     * @throws BadInputFormatException if the description is badly formatted
     */
    private Event(String description) throws BadInputFormatException, InvalidDateException {
        this(parseToDescription(description), parseToDate(description));
    }

    /**
     * duke.tasks.Event constructor.
     *
     * @param description the event's description
     * @param at the event's time
     */
    private Event(String description, Date at) {
        super(description, at);
    }

    /**
     * Factory duke.tasks.Event method.
     *
     * @param description the user's input
     * @return a new duke.tasks.Event object
     * @throws BadInputFormatException if the input is badly formatted
     */
    public static Event of(String description) throws BadInputFormatException, InvalidDateException {
        return new Event(description);
    }

    private static Date parseToDate(String description) throws BadInputFormatException, InvalidDateException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return Date.of(tokens[1]);
    }

    private static String parseToDescription(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens[0];
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", isDone ? "[X]" : "[ ]", description, date);
    }
}
