package duke.util;

/**
 * Parses the commands given by a user.
 */
public class Parser {

    /**
     * Parses a done command.
     *
     * @param cmd Command to be processed.
     */
    public static int parseDoneCmd(String cmd) {
        String[] a = cmd.split(" ");
        return Integer.parseInt(a[1]);
    }

    /**
     * Checks if command is bye.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isNotBye(String cmd) {
        return !cmd.equals("bye");
    }

    /**
     * Checks if command is list.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isList(String cmd) {
        return cmd.equals("list");
    }

    /**
     * Checks if command is done.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isDone(String cmd) {
        return cmd.matches("^done [0-9]+$");
    }

    /**
     * Checks if command is a valid task.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isValidTask(String cmd) {
        return cmd.matches("^todo .*$")
                || cmd.matches("^deadline .* /by .*$")
                || cmd.matches("^event .* /at .*$");
    }

    /**
     * Checks if command is missing description.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isMissingArg(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[1].matches(" *");
    }

    /**
     * Gets the task name in a command.
     *
     * @param cmd Command to be processed.
     */
    public static String getTaskName(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[0];
    }

    /**
     * Gets the task description in a command.
     *
     * @param cmd Command to be processed.
     */
    public static String getDesc(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[1];
    }

    /**
     * Gets the deadline information in a deadline description.
     *
     * @param desc Command description to be processed.
     */
    public static String getDlInfo(String desc) {
        String[] a = desc.split(" /by ", 2);
        return a[0];
    }

    /**
     * Gets the deadline due date in a deadline description.
     *
     * @param desc Command description to be processed.
     */
    public static String getDlDue(String desc) {
        String[] a = desc.split(" /by ", 2);
        return a[1];
    }

    /**
     * Gets the event information in a event description.
     *
     * @param desc Command description to be processed.
     */
    public static String getEvInfo(String desc) {
        String[] a = desc.split(" /at ", 2);
        return a[0];
    }

    /**
     * Gets the event due date in a event description.
     *
     * @param desc Command description to be processed.
     */
    public static String getEvDue(String desc) {
        String[] a = desc.split(" /at ", 2);
        return a[1];
    }

    /**
     * Checks if command is delete.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isDelete(String cmd) {
        return cmd.matches("^delete [0-9]+$");
    }

    /**
     * Parses a delete command.
     *
     * @param cmd Command to be processed.
     */
    public static int parseDeleteCmd(String cmd) {
        String[] a = cmd.split(" ");
        return Integer.parseInt(a[1]);
    }

    /**
     * Checks if command is empty.
     *
     * @param cmd Command to be processed.
     */
    public static boolean isEmptyTask(String cmd) {
        return cmd.equals("todo")
                || cmd.matches("deadline *")
                || cmd.matches("event *");
    }

    /**
     * Converts Parser to string format.
     *
     * @return Parser as a string.
     */
    @Override
    public String toString() {
        return "parses commands";
    }
}
