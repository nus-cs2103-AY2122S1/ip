/**
 * Class to store event. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-7
 * @since 19 Aug 2021
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

    /**
     * Constructor for Events.
     *
     * @param task task to be stored
     * @param dateTime dateTime for the task
     * @param done whether the task is done
     */
    Events(String task, String dateTime, boolean done) {
        super(task, done);
        this.dateTime = dateTime;
    }

    /** Getter for dateTime.
     *
     * @return dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * To save the task to the txt file.
     * Format is as follow: <Type(E)> | <Description> | <Done> | <DateTime>
     *
     * @return string to save the txt file
     */
    public String saveOutput() {
        return String.format("D | %s | %s | %s", super.getTask(), super.getIsDone() ? 1 : 0, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
