package duke.tasks;

/**
 * Class that initializes the deadline task
 *
 */
public class Deadline extends Task {

    /** String that defines the date and time for the deadline */
    protected String by;

    /**
     * Constructor for the deadline class
     *
     * @param description String that defines the description of the task
     * @param by String that defines the date and time for the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of
     * the task data to be written into the storage file
     *
     * @return String representation of data to be written into storage file
     */
    @Override
    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "D | " + i + " | " + this.description + " | " + this.by;
    }

    /**
     * Returns the string representation of
     * the task data to be outputted
     *
     * @return String representation of data to be outputted
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
