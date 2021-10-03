package seedu.duke.tasks;

import java.util.ArrayList;

/**
 * Is a subclass of {@code Task} which emphasises on the amount of time needed
 * to complete this {@code TimedTask}.
 */
public class TimedTask extends Task {

    /**
     * Primary Constructor which initialised the description and time. Setting
     * {@code isDone} as {@code false}.
     * 
     * @param description is the description of this {@code TimedTask}.
     * @param dateTime    is the time needed for this {@code TimedTask}.
     */
    public TimedTask(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Secondary Constructor which initialised the description, time, and isDone.
     * 
     * @param description is the description of this {@code TimedTask}.
     * @param dateTime    is the time needed for this {@code TimedTask}.
     * @param isDone      checks if this {@code TimedTask} is done.
     */
    public TimedTask(String description, String dateTime, boolean isDone, ArrayList<String> tags) {
        super(description, dateTime, isDone, tags);
    }

    /**
     * Gets the symbol of this {@code TimedTask} object. The symbol for
     * {@code TimedTask} object is "TT".
     * 
     * @return "TT"
     */
    @Override
    public String getSymbol() {
        return "TT";
    }

    /**
     * Marks the current {@code TimedTask} as done.
     * 
     * @return a new {@code TimedTask} object with the same description, but setting
     *         {@code isDone} property to be true.
     */
    @Override
    public TimedTask markAsDone() {
        return new TimedTask(this.getDescription(), this.getDateTime(), true, this.getTags());
    }

    /**
     * Describes the current {@code TimedTask} object.
     * 
     * @return a description of the current {@code TimedTask} object.
     */
    @Override
    public String toString() {
        String str = "[TT][" + this.getStatusIcon() + "] " + this.getDescription() + " (needs " + this.getDateTime() + ")";
        str = this.addTagsToString(str);
        return str;
    }
}
