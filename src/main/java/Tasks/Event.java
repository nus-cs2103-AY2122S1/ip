package Tasks;

/**
 * Event task which contains the task description, and the date of the event
 */
public class Event extends Task {

    private String date;

    /**
     * A public constructor to create an Event task
     *
     * @param description description of the deadline task
     * @param date        the date stated after "/at " portion
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * A public toString method to add the task type [E] in front of the checkbox, and the date behind the task description
     *
     * @return the string representation of an event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
