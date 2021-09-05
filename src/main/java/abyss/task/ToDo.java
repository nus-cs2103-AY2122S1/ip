package abyss.task;

import java.time.LocalDate;

import abyss.exception.InvalidCommandException;

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

    @Override
    public void setDate(LocalDate date) throws InvalidCommandException {
        throw new InvalidCommandException("Todo does not have a date field to be edited.");
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
