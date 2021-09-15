package angrybot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the representation of an event.
 * The event has a description of what is it about,
 * and when is the event occurring.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Event extends TaskWithDateTime {

    /**
     * Constructor of an Event task.
     *
     * @param description The description of event.
     * @param isDone      Boolean representing if user went for the event.
     * @param when        The datetime of the event occurring.
     */
    public Event(String description, boolean isDone, LocalDateTime when) {
        super(description, isDone, 'E', when);
    }

    /**
     * Returns a string containing the type of task,
     * whether the user went for the event, the description
     * of the event, and the datetime of it occurring.
     *
     * @return The string containing the information of the event.
     */
    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + showWhen();
    }

    /**
     * Returns a string containing the datetime of the event,
     * in the format of dd MMM yyyy HHmm.
     *
     * @return The string containing the information on when the event is happening.
     */
    public String showWhen() {
        return String.format("(at: %s)", dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }

    /**
     * Returns a string representation of the event task.
     * Used in storing of data to local directory.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("|%s", dateTime);
    }
}
