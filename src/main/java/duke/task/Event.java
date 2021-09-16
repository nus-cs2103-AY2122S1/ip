package duke.task;

public class Event extends Task {
    protected String period;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param period Time period of the event.
     */
    protected Event(String description, String period) {
        super(description);
        assert period != null : "period is null";

        this.period = period;
    }

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param period Time period of the event.
     * @param isDone Has the event been done?
     */
    protected Event(String description, String period, boolean isDone) {
        this(description, period);
        this.isDone = isDone;
    }

    @Override
    public String populateSaveData() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.period;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((Event) o).isDone == this.isDone
                    && ((Event) o).description.equals(this.description)
                    && ((Event) o).period.equals(this.period);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
