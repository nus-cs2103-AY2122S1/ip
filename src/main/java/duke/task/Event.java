package duke.task;

public class Event extends Task {
    /** The information on the time of the Event. */
    private String time;

    /**
     * Constructs a new Event task.
     *
     * @param name The name of the new Event task.
     * @param time The information on time of the Event task.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    /**
     * Converts the Event task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Event task.
     */
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat() + "|" + time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
