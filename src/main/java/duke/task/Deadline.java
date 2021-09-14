package duke.task;

/**
 * Deadline class which encapsulates task's deadline.
 */
public class Deadline extends Task {
    private String byDate;

    /**
     * Constructor method of Deadline.
     *
     * @param description Description of a deadline.
     * @param by Due time of a deadline.                   
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
    }

    /**
     * Returns the deadline in array format.
     *
     * @return Deadline in array format.
     */
    @Override
    public String[] formatTaskInArray() {
        String doneIndicator;
        if (this.isDone()) {
            doneIndicator = "1";
        } else {
            doneIndicator = "0";
        }
        String[] string = new String[]{"D", doneIndicator, this.getDescription()};
        return string;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}