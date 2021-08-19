/**
 * Class for the deadline task
 *
 * @author  Yim Jaewon
 */
public class Deadline extends Task {

    /**
     * the date or time of the event.
     */
    protected String by;

    /**
     * The constructor of the deadline task.
     *
     * @param description the description of the deadline task.
     * @param by the date or time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * override toString method for easier printing.
     *
     * @return the stingified task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}