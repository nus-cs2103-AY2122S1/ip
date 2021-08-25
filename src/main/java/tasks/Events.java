package tasks;

public class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * A method that overwrites the toStringForFile() method
     * in tasks
     *
     * @return the String representation of Event, to be written into the file
     */
    @Override
    public String toStringForFile() {
        return "E - " + super.toStringForFile() + " - " + at;
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
