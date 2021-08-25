/**
 * This class represents an Event, which is a Task with a timing.
 */

public class Event extends Task {
    String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Gives the type of the task.
     *
     * @return E for event.
     */
    @Override
    public String type() {
        return "E";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo() + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + this.time + ")";
    }
}
