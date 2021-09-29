package duke.task;

import duke.misc.Parser;

/**
 * Event class which represents a task with event date/time.
 */
public class Event extends Task {
    private final DateTime atDateTime;

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param atDate Date of event.
     * @param atTime Time of event.
     */
    public Event(String description, String atDate, String atTime) {
        super(description);
        String[] timeRange = Parser.parseEventTime(atTime);
        this.atDateTime = new DateTime(atDate, timeRange[0], timeRange[1]);
    }

    /**
     * Constructor for Event class.
     *
     * @param description Description of event task.
     * @param atDate Date of event.
     * @param atTime Time of event.
     * @param isDone Completion status of event.
     */
    public Event(String description, String atDate, String atTime, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        String[] timeRange = Parser.parseEventTime(atTime);
        String startTime = timeRange[0];
        String endTime = timeRange[1];
        this.atDateTime = new DateTime(atDate, startTime, endTime);
    }

    /**
     * Formats task's data into a string for storage in duke.txt.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Event // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + atDateTime.getDate() + " // " + atDateTime.getTimeRange();
    }

    /**
     * Returns a String describing details of the event task.
     *
     * @return A String Describing details of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: "
                + atDateTime.getFormattedDate()
                + " "
                + atDateTime.getFormattedTimeRange()
                + ")\n";
    }
}
