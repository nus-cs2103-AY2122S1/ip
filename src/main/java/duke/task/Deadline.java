package duke.task;

/**
 * A task of type deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Instantiates a new object of the deadline class.
     *
     * @param description description of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Makes deep copy of itself.
     *
     * @return Deadline deep copy of itself.
     */
    @Override
    public Task duplicate() {
        return new Event(this.description, this.by);
    }

    /**
     * Generates the string representation of event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "D " + super.toString() + " | " + by;
    }
}
