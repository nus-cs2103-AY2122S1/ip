package seedu.duke.tasks;

import java.util.ArrayList;

/**
 * Is a subclass of {@code Task} which emphasises due date in {@code Deadline}.
 */
public class Deadline extends Task {

    /**
     * Constructor. Default having the {@code isDone} parameter to be set as
     * {@code false}.
     * 
     * @param description description of this {@code Deadline} object.
     * @param dateTime    is the description of the location and time for the
     *                    {@code Deadline}.
     */
    public Deadline(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description is the description of the {@code Deadline}.
     * @param dateTime    is the description of the location and time for this
     *                    {@code Deadline}.
     * @param isDone      determine whether this {@code Deadline} is completed or
     *                    not.
     * @param tags        is an {@code ArrayList<String>} of tags tied to this
     *                    {@code Deadline}.
     */
    public Deadline(String description, String dateTime, boolean isDone, ArrayList<String> tags) {
        super(description, dateTime, isDone, tags);
    }

    /**
     * Retrieves the symbol of this {@code Deadline}. Different {@code Task} have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "D".
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * Marks this {@code Deadline} as done.
     * 
     * @return a new {@code Deadline} with the same description and by, but setting
     *         {@code isDone} property to be true
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getDateTime(), true, this.getTags());
    }

    /**
     * Describes this {@code Deadline}.
     * 
     * @return a description of this {@code Deadline}.
     */
    @Override
    public String toString() {
        String str = "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.getDateTime() + ")";
        str = this.addTagsToString(str);
        return str;
    }
}
