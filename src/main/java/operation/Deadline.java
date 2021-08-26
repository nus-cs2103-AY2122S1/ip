package operation;

/**
 * This is the Deadline class for deadline tasks.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline objects.
     * @param description string input
     * @param by deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Splits deadline input string into body and deadline date.
     * @param input input string
     * @return new split Deadline object
     */
    public static Deadline splitDeadline(String input) {
        String[] partsOfDeadline = input.split("/by ");
        String deadlineContent = partsOfDeadline[0].substring(9);
        String by = partsOfDeadline[1];
        return new Deadline(deadlineContent, by);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.by
                + ")";
    }
}
