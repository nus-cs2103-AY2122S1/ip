package duke.task;

/**
 * A class representing an Event task.
 */
public class Event extends Task {

    /** the timing of the Event as a String. */
    private String at;

    /**
     * Constructs an Event object using the given task details and timing of the event.
     * @param taskDetails The given task details.
     * @param at the timing of the event as a String.
     */
    public Event (String taskDetails, String at) {
        super(taskDetails);
        this.at = at;
    }

    /**
     * Returns a letter as a String that represents the task type.
     *
     * @return The letter "E" that represents an Event.
     */
    public String taskType() {
        return "E";
    }

    /**
     * Returns the String representation of the task details and timing of the Event.
     * This String will be used to save to the data file.
     *
     * @return The String representation of the task details and timing of the Event.
     */
    @Override
    public String getTaskDetails() {
        return super.getTaskDetails() + " | " + at;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at :" + at + ")";
    }
}
