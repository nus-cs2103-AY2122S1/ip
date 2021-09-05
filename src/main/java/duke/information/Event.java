package duke.information;

/**
 * Class that stores a Task that contains a location.
 */
public class Event extends Task {

    /** Location of the event. */
    protected String location;

    /**
     * Constructs Event class.
     *
     * @param description Event description.
     * @param location Location of the event.
     */
    public Event(String description, String location) {
        super(description);
        this.location = location;
    }

    /**
     * Converts the event's information into a string.
     * To be stored in the user's dedicated txt file.
     *
     * @return String of the event information.
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + this.location + "\n";
    }

    /**
     * Converts the event's information into a string.
     * To be used to display the event information to the user.
     *
     * @return String of the event information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + location + ")";
    }
}