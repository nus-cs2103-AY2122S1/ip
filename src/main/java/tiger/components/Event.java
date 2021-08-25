package tiger.components;

import tiger.constants.Priority;
import tiger.exceptions.inputs.TigerDateParsingException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.utils.CustomDate;
import tiger.utils.DateStringConverter;

public class Event extends Task {

    /** Date event is at. */
    private final CustomDate date;

    /**
     * Public constructor for {@code Event} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     * @param date A {@code CustomDate object}.
     */

    public Event(String taskDescription, boolean done, CustomDate date) {
        super(taskDescription, done);
        this.priority = Priority.MEDIUM;
        this.date = date;
    }

    public Event(String taskDescription, boolean done, CustomDate date, Priority priority) {
        super(taskDescription, done);
        this.priority = priority;
        this.date = date;
    }

    /**
     * Mark a {@code Event} as done.
     */

    @Override
    public Event markDone() {
        return new Event(this.taskDescription, true, this.date);
    }

    /**
     * Gets the String representation of {@code Event}.
     *
     * @return the String representation of {@code Event}.
     */
    @Override
    public String toString() {
        if (this.done) {
            return String.format("[E] [X] %s \t(at %s)", this.taskDescription, this.date.toString());
        } else {
            return String.format("[E] [%s] %s \t(at %s)", this.getPriority().getLetter(),
                    this.taskDescription,
                    this.date.toString());
        }
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    protected String getStorageRepresentation() {
        return String.format("E;%s;%s;%s;%s", this.done, this.taskDescription, this.date.toString(),
                this.getPriority().getLetter());
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Event}
     *
     * @param s String loaded from storage.
     * @return the corresponding {@code Event} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static Event getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        DateStringConverter dateStringConverter = new DateStringConverter();
        String[] stringArray = s.split(";");
        int length = stringArray.length;
        try {
            assert (length == 5);
            // check if task is indeed a Event task
            assert (stringArray[0].equals("E"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            // check that the event timing is non-empty
            assert (!stringArray[3].equals(""));
            assert (stringArray[4].equals("L") || stringArray[4].equals("M") || stringArray[4].equals("H"));
            Priority p;
            switch (stringArray[4]) {
            case "L":
                p = Priority.LOW;
                break;
            case "H":
                p = Priority.HIGH;
                break;
            default:
                p = Priority.MEDIUM;
            }
            if (stringArray[1].equals("true")) {
                // task description, done, timing
                return new Event(stringArray[2], true, dateStringConverter.getDateFromString(stringArray[3]), p);
            } else {
                return new Event(stringArray[2], false, dateStringConverter.getDateFromString(stringArray[3]), p);
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException(e.toString());
        } catch (TigerDateParsingException e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }
}
