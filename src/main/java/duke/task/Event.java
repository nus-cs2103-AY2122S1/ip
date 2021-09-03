package duke.task;

import org.json.simple.JSONObject;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;

/** Represents an Event object. */
public class Event extends DatedTask {
    /** An enumeration that represents the start and end date of the event. */
    private enum Range {
        START, END
    }

    /** The date when the event ends. */
    private final Date until;

    /**
     * Event constructor.
     *
     * @param description The event's description.
     * @throws BadInputFormatException If the description is badly formatted.
     */
    Event(String description) throws BadInputFormatException, InvalidDateException {
        this(parseToDescription(description),
                parseToDate(parseToDates(description), Range.START),
                parseToDate(parseToDates(description), Range.END));
    }

    /**
     * Event constructor.
     *
     * @param description The event's description.
     * @param start The event's start time.
     * @param until The event's end time.
     */
    Event(String description, Date start, Date until) {
        super(description, start);
        this.until = until;
    }

    /**
     * Event constructor.
     *
     * @param description The event's description.
     * @param start The event's start time.
     * @param until The event's end time.
     * @param isDone The event's done status.
     */
    private Event(String description, Date start, Date until, boolean isDone) {
        super(description, start, isDone);
        this.until = until;
    }

    /**
     * Factory Event method.
     *
     * @param description The user's input.
     * @return An Event object.
     * @throws BadInputFormatException If the input is badly formatted.
     */
    public static Event of(String description) throws BadInputFormatException, InvalidDateException {
        return new Event(description);
    }

    /**
     * Factory Event method.
     *
     * @param description The Event's description.
     * @param start The Event's start date.
     * @param until The Event's end date.
     * @param isDone The Event's done status.
     * @return An Event object.
     */
    public static Event of(String description, Date start, Date until, boolean isDone) {
        return new Event(description, start, until, isDone);
    }

    /**
     * Helper method to parse the user's input into strings containing dates by splitting using regex delimited by the
     * "/at" keyword.
     *
     * @param description The user's input.
     * @return A Date object.
     * @throws BadInputFormatException If the given input is not well formatted.
     */
    private static String parseToDates(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) { // Guard clause
            throw new BadInputFormatException();
        }
        return tokens[1];
    }

    /**
     * Helper method to parse a string containing a range of dates  into Date objects by splitting using regex
     * delimited by the "/to" keyword.
     *
     * @param dateString The range of dates in a string.
     * @param range The enum which indicates whether to return the beginning date or end date.
     * @return A Date object.
     * @throws BadInputFormatException If the given input is not well formatted.
     * @throws InvalidDateException If the date contained in the input does not adhere to the yyyy-MM-dd format.
     */
    private static Date parseToDate(String dateString, Range range)
            throws BadInputFormatException,
            InvalidDateException {
        String[] tokens = dateString.split(" /to ");
        Date date;
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        switch (range) {
        case START:
            date = Date.of(tokens[0]);
            break;
        case END:
            date = Date.of(tokens[1]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + range);
        }
        return date;
    }

    /**
     * Helper method to parse the user's input into the Event description by splitting using regex delimited by the
     * "/at" keyword.
     *
     * @param description The user's input.
     * @return The description of the Deadline as string.
     * @throws BadInputFormatException If the given input is not well formatted.
     */
    private static String parseToDescription(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) { // Guard clause
            throw new BadInputFormatException();
        }
        return tokens[0];
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        assert description != null;
        return String.format("[E]%s %s (at: %s to %s)", isDone ? "[X]" : "[ ]", description, date, until);
    }

    /**
     * Returns a JSON representation of the Todo object.
     *
     * @return A JSON representation of the Todo object.
     */
    @Override
    @SuppressWarnings("unchecked") // Type warning due to JSON simple library. Type safety guaranteed. Just use it.
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "event");
        obj.put("description", description);
        obj.put("isDone", isDone);
        obj.put("from", date.toJsonString());
        obj.put("until", until.toJsonString());
        return obj;
    }
}
