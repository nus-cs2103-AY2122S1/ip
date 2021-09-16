package duke.task;

import duke.DukeException;

/**
 * A Task encapsulating an event with a date and time.
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
     * @param description The description of the Event.
     * @param dateAndTime Format: "yyy-mm-dd hhmm" (24h time)
     * @param isDone Whether the task is done.
     */
    public Event(String description, String dateAndTime, boolean isDone) throws DukeException {
        super(description, dateAndTime, isDone);
    }

    /**
     * @return The save string of this task.
     */
    @Override
    public String getSave() {
        return "E" + super.getSave();
    }

    /**
     * @return The display string of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
