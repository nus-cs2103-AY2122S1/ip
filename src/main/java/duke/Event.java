package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event Task that occurs at a specified time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    public Event(String name, LocalDateTime at, Boolean isDone) {
        super(name,'E', isDone);
        this.at = at;
    }

    /**
     * Returns a string representation of an Event.
     * The type of task and whether the task is done are shown.
     * Time of the event will be shown in the format "MMM d yyyy HH:mm".
     * @return String representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Parses the string passed into Duke. Returns an Event if valid,
     * else throws an Exception.
     *
     * @param str the description of the event task to be created
     * @return an Event that occurs at a specified time
     * @throws TaskException
     */
    public static Task parseCommand(String str) throws TaskException {
        String[] detailE = str.split(" /at ", 2);
        if (detailE.length == 1) {
            throw new TaskException("When is the event?");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime atTime = LocalDateTime.parse(detailE[1], formatter1);
        Event newE = new Event(detailE[0], atTime, false);
        return newE;
    }

    /**
     * Returns a string representation of an Event task is saved in the database.
     *
     * @return string representation of saved Event
     */
    @Override
    public String toSavedAs() {
        return (super.toSavedAs() + "|" + this.at);
    }
}