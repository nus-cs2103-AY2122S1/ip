/**
 * This class encapsulates the Event Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke.tasks;

public class Event extends Task {
    protected final String eventDetails;

    public Event(String description, String eventDetails) {
        super(description);
        this.eventDetails = eventDetails;
    }

    public Event(String description, String eventDetails, boolean isDone) {
        super(description);
        this.eventDetails = eventDetails;
        this.isDone = isDone;
    }

    /**
     * This function returns the string of the task to be represented in the list.
     *
     * @returns the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + description + " (at: " + eventDetails + ")";
    }

    /**
     * This function returns the string of the task to be represented in the text file.
     *
     * @returns the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.eventDetails; }
}
