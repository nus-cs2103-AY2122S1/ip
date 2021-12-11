package duke.tasks;

/**
 * Represents an event entry in the task list.
 */
public class Events extends Task {

    private String dateTimeAt;

    /**
     * Constructor for Events.
     *
     * @param description the description of this event.
     * @param dateTimeAt the time/date/period at which this event is to occur.
     * @param isDone whether this task is to be marked as done or not.
     */
    public Events(String description, String dateTimeAt, boolean isDone) {
        super(description, isDone);
        this.dateTimeAt = dateTimeAt;
    }

    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("E,%c,%s,%s", isDone01, this.description, this.dateTimeAt);
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (%s:%s)",
                this.dateTimeAt.substring(0, 2), this.dateTimeAt.substring(2));
    }

}
