package duke.task;

import java.time.format.DateTimeParseException;

import duke.util.DateTime;
import duke.util.DukeException;

/**
 * Event is created by 'event eat breakfast /at 0800'.
 * Events are a type of Task.
 */
public class Event extends Task {
    public static final char SYMBOL = 'E';

    private static final String EVENT_DELIMITER = "/at";

    /* Messages */
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command."
            + " Use 'event <text> /at <datetime>'";
    private static final String INVALID_EVENT_FORMAT_MESSAGE = "Invalid DateTime format for event. "
            + "DateTime must be in the format of yyyy-MM-dd HHmm (2019-02-01 1800)";
    private static final String MISSING_EVENT_MESSAGE = "Some arguments are missing. Use 'event <text> /at <datetime>'";
    private static final String INVALID_SAVE_MESSAGE = "Event save is given in the wrong format";

    private DateTime eventDatetime;

    /**
     * Represents the constructor for the event object.
     *
     * @param text The description of the event.
     * @param eventDatetime The date where it is due, to be converted to a DateTime object.
     * @param isDone Whether the Event is finished.
     */
    private Event(String text, String eventDatetime, boolean isDone) throws DukeException {
        super(text, isDone);
        try {
            this.eventDatetime = new DateTime(eventDatetime);
        } catch (DateTimeParseException err) {
            throw new DukeException(INVALID_EVENT_FORMAT_MESSAGE);
        }
    }

    /**
     * Acts as the factory method for creating an event object.
     *
     * @param input The remaining input after the initial 'event' string.
     * @param isDone Whether the Event is finished.
     * @return An Event object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static Event createNewEvent(String input, boolean isDone) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_EVENT_MESSAGE);
        }

        String[] eventInfo = input.split(EVENT_DELIMITER);
        if (eventInfo.length < 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String event = eventInfo[1].trim();
        String eventText = eventInfo[0].trim();
        return new Event(eventText, event, isDone);
    }

    /**
     * Acts as the factory method for creating an event object from taskList.txt.
     *
     * @param input The remaining string after the 'E |' string.
     * @return A Event object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static Event createNewEventFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String doneString = inputArr[0].trim();
        String eventText = inputArr[1].trim();
        String event = inputArr[2].trim();
        return new Event(eventText, event, doneString.equals("1"));
    }

    /**
     * Gets the format of the Event in taskList.txt.
     *
     * @return The String format of the Event in taskList.txt.
     */
    public String getSaveFormat() {
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(), this.getText(),
                this.eventDatetime.getSaveFormat());
    }

    /**
     * Gets the format of the event in console.
     *
     * @return The String format of the event in console.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime);
    }
}
