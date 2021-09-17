/**
 * This class encapsulates the Deadline Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Deadline extends Task {

    protected ArrayList<String> tags = new ArrayList<>();
    private final LocalDateTime dateTime;


    // This is used only for loading from the storage
    /**
     * Constructs a Deadline object.
     *
     * @param description Description of Deadline object
     * @param isDone whether the task has been completed
     * @param dateTimeBy The date the deadline task is due by.
     */
    public Deadline(String description, LocalDateTime dateTimeBy, boolean isDone, ArrayList<String> tags) {
        super(description);
        this.dateTime = dateTimeBy;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of Deadline object
     * @param dateTime The date and time the deadline task is due by.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.isDone = false;
    }

    /**
     * Returns the string of the task to be represented in the list.
     *
     * @return the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return String.format("[D]%s%s (by: %s) %s", super.toString(), description,
                dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")), getTags(this.tags));
    }

    /**
     * Returns the string of the task to be represented in the text file.
     *
     * @return the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return String.format("D@%d@%s@%s@%s@", (isDone ? 1 : 0), this.description, this.dateTime.toString(),
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
