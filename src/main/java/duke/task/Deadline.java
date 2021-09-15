package duke.task;

import java.time.format.DateTimeParseException;

import duke.util.DateTime;
import duke.util.DukeException;

/**
 * Deadline is created by 'deadline eat breakfast /by 0800'.
 * Deadlines are a type of Task.
 */
public class Deadline extends Task {
    public static final char SYMBOL = 'D';

    private static final String DEADLINE_DELIMITER = "/by";

    /* Messages */
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. "
            + "Use 'deadline <text> /by <datetime>'";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Invalid DateTime format for deadline. "
            + "DateTime must be in the format of yyyy-MM-dd HHmm (2019-02-01 1800)";
    private static final String MISSING_DEADLINE_MESSAGE = "Some arguments are missing. "
            + "Use 'deadline <text> /by <datetime>'";
    private static final String INVALID_SAVE_MESSAGE = "Deadline save is given in the wrong format";

    private DateTime dueDate;

    /**
     * Represents the constructor for the deadline object.
     *
     * @param text The description of the deadline.
     * @param dueDate The date where it is due, to be converted to a DateTime object.
     * @param isDone Whether the Deadline is finished.
     */
    private Deadline(String text, String dueDate, boolean isDone) throws DukeException {
        super(text, isDone);
        try {
            this.dueDate = new DateTime(dueDate);
        } catch (DateTimeParseException err) {
            throw new DukeException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
    }

    /**
     * Acts as the factory method for creating a deadline object.
     *
     * @param input The remaining input after the initial 'deadline' string.
     * @param isDone Whether the Deadline is finished.
     * @return A Deadline object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static Deadline createNewDeadline(String input, boolean isDone) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_DEADLINE_MESSAGE);
        }

        String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
        if (deadlineInfo.length < 2) {
            throw new DukeException(INVALID_DEADLINE_MESSAGE);
        }
        String deadline = deadlineInfo[1].trim();
        String deadlineText = deadlineInfo[0].trim();
        return new Deadline(deadlineText, deadline, isDone);
    }

    /**
     * Acts as the factory method for creating a deadline object from taskList.txt.
     *
     * @param input The remaining string after the 'D |' string.
     * @return A Deadline object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static Deadline createNewDeadlineFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String doneString = inputArr[0].trim();
        String deadlineText = inputArr[1].trim();
        String deadline = inputArr[2].trim();
        return new Deadline(deadlineText, deadline, doneString.equals("1"));
    }

    /**
     * Gets the format of the Deadline in taskList.txt.
     *
     * @return The String format of the Deadline in taskList.txt.
     */
    public String getSaveFormat() {
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(),
                this.getText(), this.dueDate.getSaveFormat());
    }

    /**
     * Gets the format of the deadline in console.
     *
     * @return The String format of the deadline in console.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", SYMBOL, super.toString(), this.dueDate);
    }
}
