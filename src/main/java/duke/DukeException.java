package duke;

public class DukeException extends Exception {
    private static final String INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EMPTY_DESC = "OOPS!!! The description of a task cannot be empty.";
    private static final String EMPTY_LIST = "It looks like your list is empty. Add a task first!\n"
            + "Add a task by typing [todo TASKNAME], "
            + "[deadline TASKNAME /by TIME], or [event TASKNAME /at TIME]";
    private static final String EMPTY_TIME = "It looks like you missed a command.\n"
            + "For deadlines, add a [/by dd/mm/yyyy hh:mma]. "
            + "For events, add a [/at dd/mm/yyyy hh:mma].";
    private static final String INVALID_INDEX = "Something went wrong.. to mark as done,\n"
            + "format your text as <done [number]>.";


    public DukeException(String message) {
        super(message);
    }

    public static DukeException invalidCommand() {
        return new DukeException(INVALID_COMMAND);
    }

    public static DukeException emptyDesc() {
        return new DukeException(EMPTY_DESC);
    }

    public static DukeException emptyList() {
        return new DukeException(EMPTY_LIST);
    }

    public static DukeException invalidIndex() {
        return new DukeException(INVALID_INDEX);
    }

    public static DukeException emptyTime() {
        return new DukeException(EMPTY_TIME);
    }
}
