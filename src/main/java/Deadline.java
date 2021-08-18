/**
 * Class for Deadline, a child class of Task.
 * @author Liew Jian Hong
 */

public class Deadline extends Task{
    /**
     * The date and time of the deadline.
     */
    protected String by;

    /**
     * Constructor for a Deadline task.
     * @param desc String array consisting of parsed description.
     */
    public Deadline(String[] desc) {
        super(desc[1], false);
        this.by = desc[2];
    }

    /**
     * Return a string representation of the deadline.
     * @return Return the type, completion status and description of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")\n";
    }
}
