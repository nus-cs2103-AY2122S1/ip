package duke.task;

/**
 * A type of task with information on when it is meant to occur.
 */
public class Event extends Task {
    /** The information on the time of the Event. */
    private final String TIME;

    /**
     * Constructs a new Event task.
     *
     * @param name The name of the new Event task.
     * @param time The information on time of the Event task.
     */
    public Event(String name, String time) {
        super(name);
        this.TIME = time;
    }

    /**
     * Converts the Event task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Event task.
     */
    public String convertToSaveFormat() {
        return "E|" + super.convertToSaveFormat() + "|" + TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + TIME + ")";
    }
}
