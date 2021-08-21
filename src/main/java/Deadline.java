/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task{
    protected String ddl;

    /**
     * The construction method for a deadline.
     *
     * @param description detail of a deadline
     * @param ddl time of a deadline
     */
    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    /**
     * The toString method to output a deadline.
     *
     * @return return the string form of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
