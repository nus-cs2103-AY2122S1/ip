/**
 * This class encapsulates the Todo Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

import java.util.ArrayList;

public class Todo extends Task {

    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructs a Todo object. This is used in creating a new Todo object.
     *
     * @param description The description of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object based on previous saved data. This is used when loading the saved file.
     *
     * @param description The description of the Todo object.
     * @param isDone Whether the Todo object has been done or yet.
     */
    public Todo(String description, boolean isDone, ArrayList<String> tags) {
        super(description);
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Returns the string of the task to be represented in the list.
     *
     * @return the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s %s", super.toString(), description, getTags(this.tags));
    }

    /**
     * Returns the string of the task to be represented in the text file.
     *
     * @return the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return String.format("T@%d@%s@ @%s@", (isDone ? 1 : 0), this.description, getTagsForStorage(this.tags));
        // return "T@" + (isDone ? 1 : 0) + "@" + this.description;
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
