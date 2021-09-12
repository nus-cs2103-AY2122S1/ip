package duke;

/**
 * Class to contain constants required to run duke.
 */
public class DukeConstants {
    /** determines whether user input is being accepted. */
    public static boolean isRunning;

    /** latest command that can be undone. */
    public static String prevCommand;

    /** checks whether command can be undone. */
    public static boolean isUndoable;

    /** the recently deleted task */
    public static String deleteTask;
}
