package pib.utility;

/**
 * Public class to store all error messages
 */
public class Messages {
    public static final String DIVIDER = "____________________________________________________________\n";
    public static final String ERROR_EMPTY_LIST =
            "Task list is currently empty\n";
    public static final String ERROR_EMPTY_TASK_DESCRIPTION =
            "Task description can't be blank\n";
    public static final String ERROR_EMPTY_QUERY =
            "Tell me what word you are looking for!\n";
    public static final String ERROR_DEADLINE_WRONG_FORMAT =
            "Please format the command as: deadline <task> /by <yyyy-mm-dd> <hhmm>\n";
    public static final String ERROR_EVENT_WRONG_FORMAT =
            "Please format the command as: event <task> /at <yyyy-mm-dd> <hhmm>\n";
    public static final String ERROR_DATETIME_WRONG_FORMAT =
            "Ensure date-time format is YYYY-MM-DD HHMM\n";
    public static final String ERROR_BLANK_MARK_AS_DONE_INDEX =
            "Tell me which item to mark as done!\n";
    public static final String ERROR_IOOB_EXCEPTION =
            "Please enter a valid task number!\n";
    public static final String ERROR_BLANK_DELETE_INDEX =
            "Tell me which item to delete!\n";
    public static final String ERROR_ALREADY_MARKED_AS_DONE =
            "Item is already marked as done!\n";
    public static final String ERROR_LOADING_SAVED_DATA =
            "Error loading saved data\n";
    public static final String ERROR_SAVING_DATA =
            "Error saving data\n";
    public static final String ERROR_FNF_EXCEPTION =
            "Error locating file\n";
    public static final String ERROR_IO_EXCEPTION =
            "IOException\n";
    public static final String ERROR_UNKNOWN_COMMAND =
            "Uh oh :( I don't know that command\n";
    public static final String ERROR_EDIT_WRONG_FORMAT =
            "Please format command as: edit <task number> </i or /d or /t> <new value>";
    public static final String ERROR_NO_TODO_DATE =
            "Todo tasks do not contain date";
    public static final String ERROR_INVALID_UPDATE_PART =
            "Use /i to update task Information, /d to update task Date and /t to update task Time";
    public static final String ERROR_EMPTY_NEW_VALUE =
            "New value cannot be blank";
    public static final String ERROR_EDIT_DATETIME_WRONG_FORMAT =
            "Ensure date format is YYYY-MM-DD or time format is HHMM\n";
    public static final String ERROR_DEFAULT =
            "Error Occurred!";
}
