package duke.task;

import org.json.simple.JSONObject;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;

/** Represents an Event object. */
public class Event extends DatedTask {
    /**
     * Event constructor.
     *
     * @param description The event's description
     * @throws BadInputFormatException If the description is badly formatted
     */
    Event(String description) throws BadInputFormatException, InvalidDateException {
        this(parseToDescription(description), parseToDate(description));
    }

    /**
     * Event constructor.
     *
     * @param description the event's description
     * @param at the event's time
     */
    Event(String description, Date at) {
        super(description, at);
    }

    private Event(String description, Date at, boolean isDone) {
        super(description, at, isDone);
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
     * @param at The Event's date.
     * @param isDone The Event's done status.
     * @return An Event object.
     */
    public static Event of(String description, Date at, boolean isDone) {
        return new Event(description, at, isDone);
    }

    /**
     * Helper method to parse the user's input into a Date object by splitting using regex delimited by the "/at"
     * keyword.
     *
     * @param description The user's input.
     * @return A Date object.
     * @throws BadInputFormatException If the given input is not well formatted.
     * @throws InvalidDateException If the date contained in the input does not adhere to the yyyy-MM-dd format.
     */
    private static Date parseToDate(String description) throws BadInputFormatException, InvalidDateException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return Date.of(tokens[1]);
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
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens[0];
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", isDone ? "[X]" : "[ ]", description, date);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "event");
        obj.put("description", description);
        obj.put("isDone", isDone);
        obj.put("date", date.toJsonString());
        return obj;
    }
}
