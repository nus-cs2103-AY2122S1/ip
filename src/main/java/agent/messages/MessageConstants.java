package agent.messages;

/**
 * Utility class containing message constants.
 *
 * @author kevin9foong
 */
public class MessageConstants {
    public static final String MESSAGE_GREETING = "Hello! Meow is Butler. \nHow may mew be of service?";
    public static final String MESSAGE_BYE = "Goodbye! Meow is sleeping now! (Shutting down...)";
    public static final String MESSAGE_TASK_ADD_HEADER = "Got it. Meow've added this task:\n\t";
    public static final String MESSAGE_TASK_DONE_HEADER = "Nice! Meow've marked this task as done:";
    public static final String MESSAGE_TASK_LIST_HEADER = "Here are the tasks in your list:";
    public static final String MESSAGE_DEADLINE_REMINDER_HEADER = "Here's the list of "
            + "incomplete tasks sorted by deadline";
    public static final String MESSAGE_FIND_TASK_LIST = "Here are the tasks with matching descriptions in your list:";
    public static final String MESSAGE_TASK_DELETE_HEADER = "Noted. Meow've removed this task:\n\t";
    public static final String MESSAGE_INVALID_COMMAND =
            "Nyaa! I'm sorry, but meow don't know what that means :-(";
    public static final String MESSAGE_EMPTY_TODO_BODY =
            "Nyaa! The description of a todo cannot be empty. :-(";
    public static final String MESSAGE_EMPTY_EVENT_BODY =
            "Nyaa! The description & at fields of an event are not provided. Try eg. "
                    + "\"event <description> /at <venue>\" instead.";
    public static final String MESSAGE_EMPTY_DEADLINE_BODY =
            "Nyaa! The description & by fields of a deadline are not provided. Try eg."
                    + "\"deadline <description> /by <yyyy-mm-dd>\" instead.";
    public static final String MESSAGE_EMPTY_TEXT_FILTER = "Oof!!! No text filter provided. "
            + "Try \"find <text filter>\" instead.";
    public static final String MESSAGE_INVALID_EVENT_FORMAT =
            "Nyaa! The format of event provided is invalid. "
                    + "Try \"event <description> /at <venue>\" instead.";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT =
            "Nyaa! The format of deadline provided is invalid. "
                    + "Try \"deadline <description> /by <yyyy-mm-dd>\" instead.";
    public static final String MESSAGE_INVALID_DATETIME_FORMAT =
            "Nyaa! The format of the date and time provided is invalid. "
                    + "Try \"yyyy-mm-dd\" instead.";
    public static final String MESSAGE_INVALID_TASK_NUMBER =
            "Nyaa! Task number provided is invalid. Please try another number.";
    public static final String MESSAGE_INVALID_INTEGER = "Nyaa! Task number must be valid integer. eg. 1";
    public static final String MESSAGE_TASK_FILE_IO_FAILURE = "Nyaa! Unable to read/write from save file.";
    public static final String MESSAGE_INVALID_TASK_DATA = "Nyaa! Saved task data is corrupted/invalid."
            + "\nButler has failed to start. Please verify/delete the saved data file! (Shutting down...)";

    // hide constructor for utility class
    private MessageConstants() {
    }
}
