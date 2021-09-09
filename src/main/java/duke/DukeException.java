package duke;

/**
 * An exception thrown by the Duke program.
 * Exceptionally boring.
 * @author Thomas Hogben
 */
public class DukeException extends Exception {
    public static final String DEFAULT = "im sorry I is no understand.";
    public static final String BLANK_DESCRIPTION = "is no leave description blank;";
    public static final String BLANK_DATE_AND_TIME = "is no leave date and time blank!";
    public static final String CORRUPT_TASK = "save file corrupt. is delete task.";
    public static final String CORRUPT_SAVE = "save file corrupt. is no exit program until message stops.";
    public static final String INVALID_DATE_AND_TIME =
            "is formatted date and time wrong. is please try again.\nformat is yyyy-mm-dd hhmm (24h time)";
    public static final String INVALID_TASK_NUMBER = "what kind of number is (||'︵'.)";
    public static final String NOT_ENOUGH_TASKS = "we is dont have that many tasks yet.";
    public static final String UNSPECIFIED_TASK = "please is specify task please,";

    /**
     * @param msg The message the DukeException should contain.
     *            The DukeException class has many to pick from.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
