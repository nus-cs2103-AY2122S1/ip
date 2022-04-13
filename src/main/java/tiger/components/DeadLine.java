package tiger.components;

import tiger.constants.Priority;
import tiger.exceptions.inputs.TigerDateParsingException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.utils.CustomDate;
import tiger.utils.DateStringConverter;

public class DeadLine extends Task {

    /** Date deadline is due by. */
    private final CustomDate date;

    /**
     * Constructor for the {@code Deadline} class.
     *
     * @param taskDescription Description of the users task.
     * @param isDone Whether the task has been completed or not.
     * @param date Date of the task.
     * @param priority The priority of the task, specified by the user (if any).
     */

    public DeadLine(String taskDescription, boolean isDone, CustomDate date, Priority priority) {
        super(taskDescription, isDone, priority);
        this.date = date;
    }

    /**
     * Mark a {@code DeadLine} as done.
     */

    @Override
    public DeadLine markDone() {
        return new DeadLine(this.getTaskDescription(), true, this.date, this.getPriority());
    }

    /**
     * Gets the String representation of {@code DeadLine}.
     *
     * @return the String representation of {@code DeadLine}.
     */

    @Override
    public String toString() {
        // TODO: make current day display as today
        if (this.taskIsDone()) {
            return String.format("[D] [X] %s \t(by %s)", this.getTaskDescription(), this.date.toString());
        } else {
            return String.format("[D] [%s] %s \t(by %s)", this.getPriority().getLetter(),
                    this.getTaskDescription(),
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
        return String.format("D;%s;%s;%s;%s", this.taskIsDone(), this.getTaskDescription(), this.date.toString(),
                this.getPriority().getLetter());
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Event}
     * @param s String loaded from storage.
     * @return the corresponding {@code Event} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static DeadLine getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        String[] stringArray = s.split(";", 5);
        DateStringConverter dateStringConverter = new DateStringConverter();
        int length = stringArray.length;
        try {
            assert (length == 5);
            // check if task is indeed a DeadLine task
            assert (stringArray[0].equals("D"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            // check that the event timing is non-empty
            assert (!stringArray[3].equals(""));
            assert (stringArray[4].equals("L") || stringArray[4].equals("M") || stringArray[4].equals("H"));
            Priority p = Priority.getPriorityFromLetter(stringArray[4]);
            if (p.equals(Priority.INVALID)) {
                // this is not needed if we compile with assertions
                throw new TigerStorageLoadException();
            }
            if (stringArray[1].equals("true")) {
                return new DeadLine(stringArray[2], true, dateStringConverter.getDateFromString(stringArray[3]), p);
            } else {
                return new DeadLine(stringArray[2], false, dateStringConverter.getDateFromString(stringArray[3]), p);
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException();
        } catch (TigerDateParsingException e) {
            throw new TigerStorageLoadException();
        }
    }


}
