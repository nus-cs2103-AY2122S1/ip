/**
 * This class implements a Deadline object which inherits from Task and has a dueBy info.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Deadline extends Task{
    /** String representing dueBy date/time of Deadline. */
    protected String dueBy;

    /** Default constructor. */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Return the string representation of Deadline
     *
     * @return The string representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dueBy + ")";
    }

}
