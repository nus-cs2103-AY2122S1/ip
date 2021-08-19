/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task{
    protected String at;

    /**
     * The construction method for an event.
     *
     * @param description detail of an event
     * @param at time of a deadline
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * The toString method to output an event.
     *
     * @return return the string form of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
