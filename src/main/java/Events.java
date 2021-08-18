/**
 * This class represents the Events event.
 * @author Nigel Tan
 */
public class Events extends Task {
    private String at;

    /**
     * Constructor
     * @param description the name of the task
     * @param pos the position in the list
     * @param at the start
     */
    public Events(String description, int pos, String at) {
        super(description, pos);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at + ")";
    }
}
