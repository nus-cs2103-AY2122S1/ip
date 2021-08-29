package duke.task;

import duke.util.DateTime;
import duke.util.DukeException;

/**
 * Event is created by 'event eat breakfast /at 0800'.
 * Events are a type of Task.
 */
public class Event extends Task{
    private final DateTime eventDatetime;

    private static final String EVENT_DELIMITER = "/at";
    public static final char SYMBOL = 'E';

    // Messages
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";
    private static final String MISSING_EVENT_MESSAGE = "Some arguments are missing. Use 'event <text> /at <datetime>'";
    private static final String INVALID_SAVE_MESSAGE = "Event save is given in the wrong format";


    /**
     * Constructor for the event object.
     *
     * @param text The description of the event
     * @param eventDatetime The date where it is due, to be converted to a DateTime object
     * @param isDone Whether the Event is finished
     */
    private Event(String text, String eventDatetime, boolean isDone) {
        super(text, isDone);
        this.eventDatetime = new DateTime(eventDatetime);
    }

    /**
     * Factory method for creating a event object.
     * @param input The remaining input after the initial 'event' string
     * @param isDone Whether the Event is finished
     * @return An Event object
     * @throws DukeException An exception thrown according to the message given
     */
    public static Event newEvent(String input, boolean isDone) throws DukeException {
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
     * Factory method for creating a event object from taskList.txt.
     * @param input The remaining string after the 'E |' string.
     * @return A Event object
     * @throws DukeException An exception thrown according to the message given
     */
    public static Event newEventFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String isDone = inputArr[0].trim();
        String eventText = inputArr[1].trim();
        String event = inputArr[2].trim();
        return new Event(eventText, event, isDone.equals("1"));
    }

    /**
     * The format of the Event in taskList.txt
     * @return The String format of the Event in taskList.txt
     */
    public String getSaveFormat() {
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(), this.getText(), this.eventDatetime);
    };

    /**
     * The format of the event in console.
     * @return The String format of the event in console.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime);
    }
}
