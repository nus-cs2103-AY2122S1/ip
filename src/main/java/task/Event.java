package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event with specific time for when the event is occurring.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.01
 * @since 0.00
 */
public class Event extends TimeTask {
    /**
     * Default constructor for Event.
     * isDone is set to false and time is set to null.
     *
     * @param description the description fo the event
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Default constructor for Event.
     * time is set to null.
     *
     * @param description the description of the event
     * @param isDone      whether the event is done
     */
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Default constructor for Event.
     *
     * @param description the description of the event
     * @param isDone      whether the event is done
     * @param time        LocalDate object time of the event
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    /**
     * Adapted constructor for Event.
     * isDone is set to false.
     *
     * @param description the description of the event
     * @param at          the time of the event with yyyy-MM-dd format
     */
    public Event(String description, String at) {
        super(description, at);
    }

    /**
     * Adapted constructor for Event.
     *
     * @param description the description of the event
     * @param isDone      whether the event is done
     * @param at          the time of the event with yyyy-MM-dd format
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone, at);
    }

    /**
     * Template: "[E][x] description (at: Month DD YYYY)" or "[E][ ] description (at: Month DD YYYY)" for done
     * and not done task respectively.
     *
     * @return the template above for Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
