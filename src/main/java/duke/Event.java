package duke;

/**
 * Encapsulates an event in the task list.
 */
public class Event extends Task {
    private String time;

    /**
     * Creates the event.
     *
     * @param description the description of this task.
     * @param time the time that the event will take place.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    /**
     * Formats the event into a string that is compliant with the save file format.
     *
     * @return a string representing the event to be saved in save file
     */
    public String saveData() {
        return "E | " + this.getStatusIcon() + " | " + description + " | " + time;
    }
}
