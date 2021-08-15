/**
 * Class to store event. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-4
 * @since 15 Aug 2021
 */
public class Events extends Task {
    private String dateTime;

    /**
     * Constructor for Events.
     *
     * @param task task to be stored
     * @param dateTime dateTime for the task
     */
    Events(String task, String dateTime) {
        super(task);
        this.dateTime = dateTime;
    }

    /** Getter for dateTime.
     *
     * @return dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
