package duke.task;

import duke.DukeException;

/**
 * A Task with a date and time.
 * @author Thomas Hogben
 */
public class Event extends DateAndTimeTask {
    private static String splitterKey = " /at ";

    /**
     * @param description The input string to create the Event.
     *                    Format: "[description] /at yyyy-mm-dd hhmm" (24h time)
     */
    public Event(String description) throws DukeException {
        super(description, splitterKey);
    }

    /**
     * @param description The input string to create the Event.
     *                    Format: "[description] /at yyyy-mm-dd hhmm" (24h time)
     * @param isDone Whether the task is done.
     */
    public Event(String description, String dateAndTime, boolean isDone) throws DukeException {
        super(description, dateAndTime, isDone);
    }

    @Override
    public String getSave() {
        return "E" + super.getSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
