/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Deadline extends Task{
    /** The name of the deadline task */
    protected String by;

    /**
     * Constructor for Deadline task
     * @param name the name of the deadline task
     * @param by the deadline of the task
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
