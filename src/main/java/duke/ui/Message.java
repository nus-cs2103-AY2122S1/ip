package duke.ui;

public class Message {
    //Welcome and Exit messages
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke :)\n"
            + "What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    //Messages for DukeException
    public static final String MESSAGE_TXT_TO_LIST_CONVERSION_ERROR = ":') OOPS!!! An error occurred while converting"
            + " the .txt file into the task list.";

    //Messages for InvalidInputException
    public static final String MESSAGE_ERROR_OCCURRED = ":') OOPS!!! Error occurred.";
    public static final String MESSAGE_INVALID_COMMAND = ":') OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_TASK_NUMBER = ":') OOPS!!! Please key in a valid task number"
            + " within the task list.";
    public static final String MESSAGE_INVALID_FIND = ":') OOPS!!! Please key in a search term.";
    public static final String MESSAGE_INVALID_EDIT_TYPE = ":') OOPS!!! Please update only the task name or task date,"
            + " with n/[updated task name] or d/[updated date and/or time]";

    //Messages for InvalidDateAndTimeException
    public static final String MESSAGE_MISSING_DEADLINE = ":') OOPS!!! Please key in a deadline with"
            + " by/[date and/or time]"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_MISSING_EVENT_TIME = ":') OOPS!!! Please key in a event time with"
            + "at/[date and/or time]"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_INVALID_DATE_AND_TIME = ":') OOPS!!! Please key in a valid date and time"
            + " in the format dd/mm/yyyy hh:mm,"
            + " providing at least a date or a time!";
    public static final String MESSAGE_INVALID_DATE = ":') OOPS!!! Please key in a valid date"
            + " in the format dd/mm/yyyy!";
    public static final String MESSAGE_INVALID_TIME = ":') OOPS!!! Please key in a valid time"
            + " in the format hh:mm!";

    //Messages for EmptyTaskNameException
    public static final String MESSAGE_EMPTY_TODO_NAME = ":') OOPS!!! The description of a todo cannot be empty."
            + "Please provide it in n/[task name].";
    public static final String MESSAGE_EMPTY_DEADLINE_NAME = ":') OOPS!!! The description of a deadline "
            + "cannot be empty."
            + "Please provide it in n/[task name].";
    public static final String MESSAGE_EMPTY_EVENT_NAME = ":') OOPS!!! The description of a event cannot be empty."
            + "Please provide it in n/[task name].";

    //Messages for EmptyCommandInformationException
    public static final String MESSAGE_EMPTY_TODO = ":') OOPS!!! Please key in the relevant details for a todo task,"
            + "in the format todo n/[task name].";
    public static final String MESSAGE_EMPTY_DEADLINE = ":') OOPS!!! Please key in the relevant details"
            + " for a deadline task,"
            + "in the format deadline n/[task name] by/[date and/or time].";
    public static final String MESSAGE_EMPTY_EVENT = ":') OOPS!!! Please key in the relevant details for a event task,"
            + "in the format event n/[task name] at/[date and/or time].";
    public static final String MESSAGE_EMPTY_EDIT = ":') OOPS!!! Please choose the task to be updated,"
            + " specifying the updated field"
            + " in the format edit [task number] n/[new task name] OR d/[new date and/or time].";

}

