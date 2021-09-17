package duke.data.task;

/**
 * Class that represents an Event task.
 *
 * @author Wang Hong Yong
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the task.
     * @param at Date of the event.
     */
    public Event(String description, String at, String[] tags) {
        super(description);
        this.at = at;
        for (String tag: tags) {
            super.addTag(tag);
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    /**
     * Formats the event task to the desirable format.
     *
     * @return A string representing the event task in the desirable format.
     */
    public String formatToWrite() {
        return String.format("E | %d | %s | %s", (super.isDone ? 1 : 0), this.at, super.formatToWrite());
    }
}
