/**
 * This class encapsulates the Event Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event extends Task {

    protected final LocalDateTime dateTimeOf;
    protected final LocalTime timeEnd;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructs an Event object.
     *
     * @param description Description of new Event object.
     * @param dateTimeOf Details of new Event object.
     */
    public Event(String description, LocalDateTime dateTimeOf, LocalTime timeEnd,
                 boolean isDone, ArrayList<String> tags) {
        super(description);
        this.dateTimeOf = dateTimeOf;
        this.timeEnd = timeEnd;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Constructs an Event object.
     *
     * @param description Description of new Event object.
     * @param dateTimeOf Details of new Event object.
     */
    public Event(String description, LocalDateTime dateTimeOf, LocalTime timeEnd) {
        super(description);
        this.dateTimeOf = dateTimeOf;
        this.timeEnd = timeEnd;
        this.isDone = false;
    }

    /**
     * This function returns the string of the task to be represented in the list.
     *
     * @return the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return String.format("[E]%s%s (at: %s - %s) %s", super.toString(), description,
                dateTimeOf.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")),
                timeEnd.format(DateTimeFormatter.ofPattern("h:mm a")),
                getTags(this.tags));
    }

    /**
     * This function returns the string of the task to be represented in the text file.
     *
     * @return the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return String.format("E@%d@%s@%s~%s@%s@", (isDone ? 1 : 0),
                this.description, this.dateTimeOf.toString(), this.timeEnd.toString(),
                getTagsForStorage(this.tags));
    }

    /**
     * Returns a String representation of the tags to any event.
     *
     * @param tagInfo The information associated with a tag.
     */
    @Override
    public void addTag(String tagInfo) {
        this.tags.add(tagInfo);
    }

}
