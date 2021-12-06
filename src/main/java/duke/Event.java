package duke;

public class Event extends Task {

    private final String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Gets the day/time the task must be completed by.
     * @return the day/time the task must be completed by
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the string representation of the Event object.
     * @return the string representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                this.getAt());
    }

    /**
     * This returns the string format the task is represented in the duke file.
     * @return the string representing an Event in the duke file
     */
    @Override
    public String print() {
        return String.format("E,%d,%s,%s",isCompleted() ? 1 : 0, this.getName(), this.getAt());
    }
}
