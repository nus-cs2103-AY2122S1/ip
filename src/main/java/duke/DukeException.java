package duke;

/**
 * An exception thrown by the Duke program.
 * Exceptionally boring.
 * @author Thomas Hogben
 */
public class DukeException extends Exception {
    public static final DukeException DEFAULT =
            new DukeException("im sorry I is no understand.");

    public static final DukeException BLANK_DESCRIPTION =
            new DukeException("is no leave description blank;");
    public static final DukeException BLANK_DATE_AND_TIME =
            new DukeException("is no leave date and time blank!");

    public static final DukeException CORRUPT_TASK =
            new DukeException("save file corrupt. is delete corrupt task.");
    public static final DukeException CORRUPT_SAVE =
            new DukeException("save file corrupt. is created new file.");

    public static final DukeException INVALID_CHARACTER =
            new DukeException("is cannot use the character '|'. is try again!");
    public static final DukeException INVALID_DATE =
            new DukeException("is formatted date wrong. is please try again.\n"
                    + "format is yyyy-mm-dd");
    public static final DukeException INVALID_TIME =
            new DukeException("is formatted time wrong. is please try again.\n"
                    + "format is hhmm (24h time)");
    public static final DukeException INVALID_TASK_NUMBER =
            new DukeException("what kind of number is >:(");
    public static final DukeException INVALID_TASK_TYPE =
            new DukeException("is the wrong task type for that. is try again?");

    public static final DukeException NOT_ENOUGH_TASKS =
            new DukeException("we is dont have that many tasks yet.");
    public static final DukeException UNSPECIFIED_TASK =
            new DukeException("please is specify task please,");

    /**
     * @param msg The message the DukeException should contain.
     *            The DukeException class has many to pick from.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
