package abyss.task;

/**
 * Represents a to-do task with a description.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do.
     *
     * @param description Description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns formatted details of the to-do.
     *
     * @return Formatted to-do details.
     */
    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }

    /**
     * Returns details of the to-do formatted for file entry.
     *
     * @return Formatted to-do details.
     */
    @Override
    public String toFileEntry() {
        return "T | " + super.getIsDone() + " | " + super.description;
    }
}
