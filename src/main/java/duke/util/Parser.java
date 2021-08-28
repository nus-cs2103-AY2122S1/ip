package duke.util;

public class Parser {

    public static int parseDoneCmd(String cmd) {
        String[] a = cmd.split(" ");
        return Integer.parseInt(a[1]);
    }

    public static boolean isNotBye(String cmd) {
        return !cmd.equals("bye");
    }

    public static boolean isList(String cmd) {
        return cmd.equals("list");
    }

    public static boolean isDone(String cmd) {
        return cmd.matches("^done [0-9]+$");
    }

    public static boolean isValidTask(String cmd) {
        return cmd.matches("^todo .*$")
                || cmd.matches("^deadline .* /by .*$")
                || cmd.matches("^event .* /at .*$");
    }

    public static boolean isMissingArg(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[1].matches(" *");
    }

    public static String getTaskName(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[0];
    }

    public static String getDesc(String cmd) {
        String[] a = cmd.split(" ", 2);
        return a[1];
    }

    public static String getDlInfo(String desc) {
        String[] a = desc.split(" /by ", 2);
        return a[0];
    }

    public static String getDlDue(String desc) {
        String[] a = desc.split(" /by ", 2);
        return a[1];
    }

    public static String getEvInfo(String desc) {
        String[] a = desc.split(" /at ", 2);
        return a[0];
    }

    public static String getEvDue(String desc) {
        String[] a = desc.split(" /at ", 2);
        return a[1];
    }

    public static boolean isDelete(String cmd) {
        return cmd.matches("^delete [0-9]+$");
    }

    public static int parseDeleteCmd(String cmd) {
        String[] a = cmd.split(" ");
        return Integer.parseInt(a[1]);
    }

    public static boolean isEmptyTask(String cmd) {
        return cmd.equals("todo")
                || cmd.matches("deadline *")
                || cmd.matches("event *")
                || cmd.matches("find *");
    }

    /**
     * Checks if command is find.
     *
     * @param cmd Command to check.
     * @return Whether command is find.
     */
    public static boolean isFind(String cmd) {
        return cmd.matches("^find .*$");
    }
}
