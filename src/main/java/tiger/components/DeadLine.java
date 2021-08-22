package tiger.components;

import tiger.exceptions.inputs.TigerDateParsingException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.utils.CustomDate;
import tiger.utils.DateStringConverter;

public class DeadLine extends Task {

    /** Date deadline is due by. */
    private final CustomDate customDate;

    /**
     * Private constructor for {@code DeadLine} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     * @param deadLine String representation of the date.
     * @throws TigerDateParsingException if the input date is invalid.
     */

    private DeadLine(String taskDescription, boolean done, String deadLine) throws TigerDateParsingException {
        super(taskDescription, done);
        this.customDate = new DateStringConverter().getDateFromString(deadLine);
    }

    /**
     * Private constructor for {@code DeadLine} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     * @param customDate A {@code CustomDate object}.
     * @throws TigerDateParsingException if the input date is invalid.
     */

    private DeadLine(String taskDescription, boolean done, CustomDate customDate) {
        super(taskDescription, done);
        this.customDate = customDate;
    }

    /**
     * Public constructor for {@code DeadLine} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     * @param deadLine String representation of the date.
     */

    public static DeadLine of(String taskDescription, boolean done, String deadLine) {
        return new DeadLine(taskDescription, done, deadLine);
    }

    /**
     * Mark a {@code DeadLine} as done.
     */

    @Override
    public DeadLine markDone() {
        return new DeadLine(this.taskDescription, true, this.customDate);
    }

    /**
     * Gets the String representation of {@code DeadLine}.
     *
     * @return the String representation of {@code DeadLine}.
     */

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[D] [X] %s (by %s)", this.taskDescription, this.customDate.toString());
        } else {
            return String.format("[D] [ ] %s (by %s)", this.taskDescription, this.customDate.toString());
        }
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    protected String getStorageRepresentation() {
        return String.format("D;%s;%s;%s", this.done, this.taskDescription, this.customDate.toString());
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Event}
     * @param s String loaded from storage.
     * @return the corresponding {@code Event} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static DeadLine getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        /* s should be of the form T|true/false|taskDescription| */
        String[] stringArray = s.split(";", 4);
        int length = stringArray.length;
        try {
            assert (length == 4);
            // check if task is indeed a DeadLine task
            assert (stringArray[0].equals("D"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            // check that the event timing is non-empty
            assert (!stringArray[3].equals(""));
            if (stringArray[1].equals("true")) {
                return new DeadLine(stringArray[2], true, stringArray[3]); // task description, done, timing
            } else {
                return new DeadLine(stringArray[2], false, stringArray[3]); // task description, done, timing
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }


}
