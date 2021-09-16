package genie.tasks;

public class Events extends Task {
    protected String at;

    public Events(String description, String at, Priority priority) {
        super(description, priority);
        this.at = at;
    }

    /**
     * A method that overwrites the toStringForFile() method
     * in genie.tasks
     *
     * @return the String representation of Event, to be written into the file
     */
    @Override
    public String toStringForFile() {
        return "E - " + super.getStatusNumber() + " - " + 
                super.description + " - " + priority + " - " + at;
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return priority + " [E]" + super.toString() + "(at: " + at + ")";
    }
}
