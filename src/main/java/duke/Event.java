package duke;

/**
 * The Event class that represents a task with a starting time.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for an Event object.
     *
     * @param desc The description of the task.
     * @param at Statement that indicates when the task starts.
     */
    public Event(String desc, String at) {
        super(desc, "E");
        this.at = at;
    }

    /**
     * A method that converts the task data into a suitable format to be saved in a save file.
     *
     * @return The formatted data as a string.
     */
    public String toData() {
        return super.toData() + "~S~" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
