package duke.ui;

public class Message {
    public static final String MESSAGE_ERROR_OCCURRED = "☹ OOPS!!! Error occurred.";
    public static final String MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "☹ OOPS!!! Please key in a valid task number within the task list.";
    public static final String MESSAGE_INVALID_FIND = "☹ OOPS!!! Please key in a search term.";

    public static final String MESSAGE_MISSING_DEADLINE = "☹ OOPS!!! Please key in a deadline with by/[date and/or time]"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_MISSING_EVENT_TIME = "☹ OOPS!!! Please key in a event time with at/[date and/or time]"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_INVALID_DATE_AND_TIME = "☹ OOPS!!! Please key in a valid date and time"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_INVALID_DATE = "☹ OOPS!!! Please key in a valid date"
            + " in the format dd/mm/yyyy!";
    public static final String MESSAGE_INVALID_TIME = "☹ OOPS!!! Please key in a valid time"
            + " in the format hh:mm!";

    public static final String MESSAGE_EMPTY_TODO_NAME = "☹ OOPS!!! The description of a todo cannot be empty."
            + "Please provide it in n/[task name].";
    public static final String MESSAGE_EMPTY_DEADLINE_NAME = "☹ OOPS!!! The description of a deadline cannot be empty."
            + "Please provide it in n/[task name].";
    public static final String MESSAGE_EMPTY_EVENT_NAME = "☹ OOPS!!! The description of a event cannot be empty."
            + "Please provide it in n/[task name].";
    public static final String MESSAGE_EMPTY_TODO = "☹ OOPS!!! Please key in the relevant details for a todo task,"
            + "in the format todo n/[task name].";
    public static final String MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! Please key in the relevant details for a deadline task,"
            + "in the format deadline n/[task name] by/[date and/or time].";
    public static final String MESSAGE_EMPTY_EVENT = "☹ OOPS!!! Please key in the relevant details for a event task,"
            + "in the format event n/[task name] at/[date and/or time].";
}

