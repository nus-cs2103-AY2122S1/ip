package messages;

/**
 * Utility class containing message constants.
 *
 * @author kevin9foong
 */
public class MessageConstants {
    // hide constructor for utility class
    private MessageConstants() {}

    public static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String TASK_ADD_HEADER = "Got it. I've added this task:\n\t";
    public static final String TASK_DONE_HEADER = "Nice! I've marked this task as done:";
    public static final String TASK_LIST_HEADER = "Here are the tasks in your list:";
    public static final String TASK_DELETE_HEADER = "Noted. I've removed this task:\n\t";

    public static final String INVALID_COMMAND_MESSAGE =
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_TODO_BODY_MESSAGE =
            "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_EVENT_BODY_MESSAGE =
            "☹ The description & at fields of an event are not provided. Try eg. " +
                    "\"event <description> /at <venue>\" instead.";
    public static final String EMPTY_DEADLINE_BODY_MESSAGE =
            "☹ The description & by fields of a deadline are not provided eg." +
                    "\"deadline <description> /by <time>\" instead";
    public static final String INVALID_EVENT_FORMAT_MESSAGE =
            "☹ The format of event provided is invalid. " +
                    "Try \"event <description> /at <venue>\" instead.";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE =
            "☹ The format of deadline provided is invalid. " +
                    "Try \"deadline <description> /by <time>\" instead.";
    public static final String INVALID_TASK_NUMBER_MESSAGE =
            "☹ Task number provided is invalid. Please try another number.";
    public static final String INVALID_INTEGER_MESSAGE = "☹ Task number must be valid integer. eg. \"done 1\"";
}
