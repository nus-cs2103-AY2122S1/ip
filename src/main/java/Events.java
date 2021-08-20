import java.text.ParseException;

/**
 * Class to store event. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-8
 * @since 20 Aug 2021
 */
public class Events extends Task {
    /**
     * Constructor for Events.
     *
     * @param task task to be stored
     * @param dateTime dateTime for the task
     * @throws ParseException Date / Time format is invalid.
     */
    Events(String task, String dateTime) throws ParseException {
        super(task, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.getDateTime() + ")";
    }
}
