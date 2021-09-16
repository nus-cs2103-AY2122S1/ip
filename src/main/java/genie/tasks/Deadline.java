package genie.tasks;

public class Deadline extends Task {

    protected String by;

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