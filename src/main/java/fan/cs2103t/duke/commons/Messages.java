package fan.cs2103t.duke.commons;

/**
 * Contains all the messages that can be displayed to users.
 */
public class Messages {

    public static final String MESSAGE_NOTHING_MATCHING_QUERY = "It seems nothing in your list "
            + "matches the search query...";
    public static final String MESSAGE_EMPTY_TODO_DESCRIPTION = "OOPS!!! "
            + "The description of a todo cannot be empty.";
    public static final String MESSAGE_EMPTY_DEADLINE_DESCRIPTION = "OOPS!!! "
            + "The description of a deadline cannot be empty.";
    public static final String MESSAGE_EMPTY_DEADLINE_DEADLINE = "OOPS!!! "
            + "The deadline of a deadline cannot be empty.";
    public static final String MESSAGE_WRONG_DEADLINE_FORMAT = "OOPS!!! "
            + "The deadline is in the wrong format.";
    public static final String MESSAGE_EMPTY_EVENT_DESCRIPTION = "OOPS!!! "
            + "The description of an event cannot be empty.";
    public static final String MESSAGE_EMPTY_EVENT_TIME = "OOPS!!! "
            + "The start/end time of an event cannot be empty.";
    public static final String MESSAGE_EMPTY_DONE_COMMAND = "Please tell me which task you have done.";
    public static final String MESSAGE_EMPTY_DELETE_COMMAND = "OOPS!!! Please tell me which task you want to delete.";
    public static final String MESSAGE_EMPTY_SEARCH_COMMAND = "OOPS!!! The search query cannot be empty.";
    public static final String MESSAGE_INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_WRITING_DATA = "OOPS!!! An error occurred when writing to the data file.";
    public static final String MESSAGE_ERROR_READING_DATA = "OOPS!!! "
            + "An error occurred when reading from the data file. "
            + "The file may be corrupted and a new list has been created.";

    public static final String MESSAGE_SUCCESSFULLY_ADDED_FORMAT = "Got it. "
            + "I've added this task: \n  %s\nNow you have %d tasks in the list.";
    public static final String MESSAGE_SUCCESSFULLY_DELETED_FORMAT = "Task %d: Noted. "
            + "I've removed this task: \n %s\n";
    public static final String MESSAGE_TASK_NOT_FOUND_FORMAT = "Task %d: Oops, the task doesn't seem to exist.\n";
    public static final String MESSAGE_TASK_ALREADY_DONE_FORMAT = "Task %d: You have already done this task!\n";
    public static final String MESSAGE_SUCCESSFULLY_DONE_FORMAT = "Task %d: "
            + "Nice! I've marked this task as done: \n %s\n";
    public static final String MESSAGE_SUCCESSFULLY_FOUND_FORMAT = "Here are the matching tasks in your list: \n%s";

}
