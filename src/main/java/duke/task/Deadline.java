package duke.task;

/**
 * A task of type deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * constructor of the class
     * @param description description of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * generates the string representation of deadline
     * @return String representation of event
     */
    @Override
    public String toString() {
        return "D " + super.toString() + " | " + by;
    }
}