package genie.tasks;

/**
 * A child class of a task that represents a task that need to be done by a specific date.
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by, Priority priority) {
        super(description, priority);
        this.by = by;
    }

    /**
     * A method that overwrites the toStringForFile() method
     * in genie.tasks
     *
     * @return the String representation of Deadline, to be written into the file
     */
    public String toStringForFile() {
        return "D - " + super.getStatusNumber() + " - " + super.description + " - " + priority + " - " + by;
    }

    /**
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return priority + " [D]" + super.toString() + " (by: " + by + ")";
    }
}