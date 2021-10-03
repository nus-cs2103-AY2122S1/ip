package seedu.duke.tasks;

import java.util.ArrayList;

/**
 * Is a subclass of {@code Task} which emphasises the location of this
 * {@code Events}.
 */
public class Events extends Task {

    /**
     * Constructor. Default having the {@code isDone} parameter to be set as
     * {@code false}.
     * 
     * @param description description of the current {@code Events} object.
     * @param dateTime    is the description of the location and time for the event.
     */
    public Events(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Second Constructor. Initialising the parameters of this {@code Events}.
     * 
     * @param description is the description of the {@code Events}.
     * @param dateTime    is the description of the location and time for the
     *                    {@code Events}.
     * @param isDone      determine whether this {@code Events} is completed or not.
     */
    public Events(String description, String dateTime, boolean isDone, ArrayList<String> tags) {
        super(description, dateTime, isDone, tags);
    }

    /**
     * Retrieves the symbol of the current object. Different {@code Task} have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "E".
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Marks the current {@code Events} as done.
     * 
     * @return a new {@code Events} object with the same {@code description} and
     *         {@code dayTime}, but setting {@code isDone} property to be
     *         {@code true}.
     */
    @Override
    public Events markAsDone() {
        return new Events(this.getDescription(), this.getDateTime(), true, this.getTags());
    }

    /**
     * Describes the current {@code Events} object.
     * 
     * @return a description of this {@code Events} object.
     */
    @Override
    public String toString() {
        String str = "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.getDateTime() + ")";
        str = this.addTagsToString(str);
        return str;
    }
}
