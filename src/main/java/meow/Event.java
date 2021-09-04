package meow;

/**
 * Represents a specific task (an event) containing the description
 * and the starting and ending time of the task that the user wants
 * to add in his or her todo list.
 */
public class Event extends Task {
    protected String at;

    /**
     * A public constructor to initialize an meow.Event object.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the meow.Event object.
     *
     * @return The string representation of the meow.Event object.
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | " + at;
    }
}
