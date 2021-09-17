/**
 * This class encapsulates the Event Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke.tasks;

import java.util.ArrayList;

public class Event extends Task {

    protected final String eventDetails;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructs an Event object.
     *
     * @param description Description of new Event object.
     * @param eventDetails Details of new Event object.
     */
    public Event(String description, String eventDetails, boolean isDone, ArrayList<String> tags) {
        super(description);
        this.eventDetails = eventDetails;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Constructs an Event object.
     *
     * @param description Description of new Event object.
     * @param eventDetails Details of new Event object.
     */
    public Event(String description, String eventDetails) {
        super(description);
        this.eventDetails = eventDetails;
        this.isDone = false;
    }

    /**
     * This function returns the string of the task to be represented in the list.
     *
     * @return the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return String.format("[E]%s%s (at: %s) %s", super.toString(), description, eventDetails,
                getTags(this.tags));
    }

    /**
     * This function returns the string of the task to be represented in the text file.
     *
     * @return the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return String.format("E@%d@%s@%s@%s@", (isDone ? 1 : 0),
                this.description, this.eventDetails, getTagsForStorage(this.tags));
        // "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.eventDetails"";
    }

}
