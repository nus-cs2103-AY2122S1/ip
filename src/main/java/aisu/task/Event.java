package aisu.task;

import aisu.exception.AisuException;

/**
 * An Event task.
 * Consists of a description and an event period.
 *
 * @author Liaw Xin Yan
 */
public class Event extends Task {
    private final String eventPeriod;

    /**
     * Constructor to initialize the Event Task with a description.
     * @param description A writeup of the task details.
     * @param eventPeriod The time period for the Event, written in any string format.
     * @throws AisuException if there is no description.
     */
    public Event(String description, String eventPeriod) throws AisuException {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    /** {@inheritDoc} */
    @Override
    public String parseData() {
        return "E;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.eventPeriod
                + '\n' + this.tagsList.parseData();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("[Event] %s %s (at: %s)%s", this.getStatusIcon(), this.description,
                this.eventPeriod, this.getTags());
    }
}
