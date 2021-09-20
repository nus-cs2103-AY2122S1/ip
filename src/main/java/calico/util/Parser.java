package calico.util;

import calico.CalicoException;
import calico.command.Deadline;
import calico.command.Event;

/**
 * Parses the commands given by a user.
 */
public class Parser {
    /**
     * Parses a done command.
     *
     * @param cmd Command to be processed.
     * @return Index of task to be marked done.
     */
    public static int parseDoneCmd(String cmd) {
        String[] a = cmd.split(" ");
        assert a.getClass().isArray() : "a should be an array";
        return Integer.parseInt(a[1]);
    }

    /**
     * Checks if command is bye.
     *
     * @param cmd Command to be processed.
     * @return Whether is bye command.
     */
    public static boolean isBye(String cmd) {
        return cmd.equals("bye");
    }

    /**
     * Checks if command is list.
     *
     * @param cmd Command to be processed.
     * @return Whether is list command.
     */
    public static boolean isList(String cmd) {
        return cmd.equals("list");
    }

    /**
     * Checks if command is done.
     *
     * @param cmd Command to be processed.
     * @return Whether is done command.
     */
    public static boolean isDone(String cmd) {
        return cmd.matches("^done [0-9]+$");
    }

    /**
     * Checks if command is a valid task.
     *
     * @param cmd Command to be processed.
     * @return Whether task is valid.
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
     * @return Whether command is missing argument.
     */
    public static boolean isMissingArg(String cmd) {
        String[] a = cmd.split(" ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[1].matches(" *");
    }

    /**
     * Gets the task name in a command.
     *
     * @param cmd Command to be processed.
     * @return Name of task.
     */
    public static String getTaskName(String cmd) {
        String[] a = cmd.split(" ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[0];
    }

    /**
     * Gets the task description in a command.
     *
     * @param cmd Command to be processed.
     * @return Description of task.
     */
    public static String getDesc(String cmd) {
        String[] a = cmd.split(" ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[1];
    }

    /**
     * Gets the deadline information in a deadline description.
     *
     * @param desc Command description to be processed.
     * @return Deadline information.
     */
    public static String getDeadlineInfo(String desc) {
        String[] a = desc.split(" /by ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[0];
    }

    /**
     * Gets the deadline due date in a deadline description.
     *
     * @param desc Command description to be processed.
     * @return Deadline due date.
     */
    public static String getDeadlineDue(String desc) {
        String[] a = desc.split(" /by ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[1];
    }

    /**
     * Gets the event information in a event description.
     *
     * @param desc Command description to be processed.
     * @return Event information.
     */
    public static String getEventInfo(String desc) {
        String[] a = desc.split(" /at ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[0];
    }

    /**
     * Gets the event due date in a event description.
     *
     * @param desc Command description to be processed.
     * @return Event due date.
     */
    public static String getEventDue(String desc) {
        String[] a = desc.split(" /at ", 2);
        assert a.getClass().isArray() : "a should be an array";
        return a[1];
    }

    /**
     * Checks if command is delete.
     *
     * @param cmd Command to be processed.
     * @return Whether task is a delete command.
     */
    public static boolean isDelete(String cmd) {
        return cmd.matches("^delete [0-9]+$");
    }

    /**
     * Parses a delete command.
     *
     * @param cmd Command to be processed.
     * @return Task index to be deleted.
     */
    public static int parseDeleteCmd(String cmd) {
        String[] a = cmd.split(" ");
        assert a.getClass().isArray() : "a should be an array";
        return Integer.parseInt(a[1]);
    }

    /**
     * Checks if command is empty.
     *
     * @param cmd Command to be processed.
     * @return Whether task command is empty.
     */
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

    /**
     * Converts Parser to string format.
     *
     * @return Parser as a string.
     */
    @Override
    public String toString() {
        return "parses commands";
    }

    /**
     * Creates a Deadline task based on a description.
     *
     * @param desc Description of task.
     * @return Deadline Task.
     * @throws CalicoException If unable to parse description properly.
     */
    public static Deadline getDeadlineTask(String desc) throws CalicoException {
        String deadlineInfo = Parser.getDeadlineInfo(desc);
        String deadlineDue = Parser.getDeadlineDue(desc);
        return new Deadline(deadlineInfo, deadlineDue);
    }

    /**
     * Creates an Event task based on a description.
     *
     * @param desc Description of task.
     * @return Event Task.
     * @throws CalicoException If unable to parse description properly.
     */
    public static Event getEventTask(String desc) throws CalicoException {
        String eventInfo = Parser.getEventInfo(desc);
        String eventDue = Parser.getEventDue(desc);
        return new Event(eventInfo, eventDue);
    }
}
