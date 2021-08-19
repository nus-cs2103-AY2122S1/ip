/**
 * This class represents the Events event.
 * @author Nigel Tan
 */
public class Events extends Task {
    private String at;

    /**
     * Constructor
     * @param description the name of the task
     * @param at the start
     */
    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
