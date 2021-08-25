package duke.task;

/**
 * This class represents a to-do - a task without any date/time attached.
 */
public class Todo extends Task {
    public static final String IDENTIFIER = "T";

    /**
     * Constructor for a to-do.
     *
     * @param description Description of the to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type identifier of a to-do.
     *
     * @return string "t" representing a to-do.
     */
    @Override
    public String getType() {
        return IDENTIFIER;
    }

    /**
     * String representation of a to-do.
     *
     * @return String representation of a to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
