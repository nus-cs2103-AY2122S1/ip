package duke.messages;

/**
 * Utility class containing message constants.
 *
 * @author kevin9foong
 */
public class MessageConstants {
    public static final String MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_TASK_ADD_HEADER = "Got it. I've added this task:\n\t";
    public static final String MESSAGE_TASK_DONE_HEADER = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASK_LIST_HEADER = "Here are the tasks in your list:";
    public static final String MESSAGE_FIND_TASK_LIST = "Here are the tasks with matching descriptions in your list:";
    public static final String MESSAGE_TASK_DELETE_HEADER = "Noted. I've removed this task:\n\t";
    public static final String MESSAGE_INVALID_COMMAND =
            "Oof!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_EMPTY_TODO_BODY =
            "Oof!!! The description of a todo cannot be empty. :-(";
    public static final String MESSAGE_EMPTY_EVENT_BODY =
            "Oof!!! The description & at fields of an event are not provided. Try eg. "
                    + "\"event <description> /at <venue>\" instead.";
    public static final String MESSAGE_EMPTY_DEADLINE_BODY =
            "Oof!!! The description & by fields of a deadline are not provided eg."
                    + "\"deadline <description> /by <yyyy-mm-dd>\" instead";
    public static final String MESSAGE_EMPTY_TEXT_FILTER = "Oof!!! No text filter provided. "
            + "Try \"find <text filter>\" instead.";
    public static final String MESSAGE_INVALID_EVENT_FORMAT =
            "Oof!!! The format of event provided is invalid. "
                    + "Try \"event <description> /at <venue>\" instead.";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT =
            "Oof!!! The format of deadline provided is invalid. "
                    + "Try \"deadline <description> /by <yyyy-mm-dd>\" instead.";
    public static final String MESSAGE_INVALID_DATETIME_FORMAT =
            "Oof!!! The format of the date and time provided is invalid. "
                    + "Try \"yyyy-mm-dd\" instead.";
    public static final String MESSAGE_INVALID_TASK_NUMBER =
            "Oof!!! Task number provided is invalid. Please try another number.";
    public static final String MESSAGE_INVALID_INTEGER = "Oof!!! Task number must be valid integer. eg. \"done 1\"";
    public static final String MESSAGE_TASK_FILE_IO_FAILURE = "Oof!!! Unable to read/write from save file.";
    public static final String MESSAGE_INVALID_TASK_DATA = "Oof!!! Saved task data is corrupted/invalid!"
            + "\nDuke failed to start. Please verify/delete the saved data file and restart Duke!";


    // hide constructor for utility class
    private MessageConstants() {
    }
}
