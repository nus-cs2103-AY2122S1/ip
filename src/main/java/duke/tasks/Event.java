package duke.tasks;

/**
 * Class that initializes the event task
 *
 */
public class Event extends Task {

    /** String that defines date and time for event */
    protected String at;

    /**
     * Constructor for event task class
     *
     * @param description String that defines the description of the task
     * @param at String that defines date and time for event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of
     * the task data to be written into the storage file
     *
     * @return String representation of data to be written into storage file
     */
    @Override
    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "E | " + i + " | " + this.description + " | " + this.at;
    }

    /**
     * Returns the string representation of
     * the task data to be outputted
     *
     * @return String representation of data to be outputted
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
