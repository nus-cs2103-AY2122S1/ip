package task;

/**
 * Deadline class.
 *
 * This class is a Task that has a 'by' datetime String.
 */
public class Deadline extends Task {

    public final static String SPLIT_WORD = "by";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Provides a formatted String of the 'by' field.
     *
     * @return formatted String for field 'by'
     */
    public String getFormattedBy() {
        return " (" + SPLIT_WORD  + ": " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getFormattedBy();
    }
}
