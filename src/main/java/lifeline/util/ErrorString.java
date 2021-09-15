package lifeline.util;

public class ErrorString {
    public static final String ERROR_DEADLINE_INCORRECT_FORMAT = "Deadline is not of the correct format! "
            + "Please use deadline <name> /by <dd/MM/yy HHmm>";
    public static final String ERROR_EVENT_INCORRECT_FORMAT = "Event date/time not in proper format! Please use event"
            + " <name> /at <dd/MM/yy> <HHmm> <HHmm>";
    public static final String ERROR_DONE_MISSING_INDEX = "You did not specify an integer! Please use done <number>";
    public static final String ERROR_DELETE_MISSING_INDEX = "You did not specify an integer! Please use delete "
            + "<number>";
    public static final String ERROR_MISSING_INDEX = "You did not specify an integer!";
    public static final String ERROR_TODO_MISSING_DETAILS = "Details of todo cannot be blank!";
    public static final String ERROR_DEADLINE_MISSING_DETAILS = "Details of deadline cannot be blank!";
    public static final String ERROR_EVENT_MISSING_DETAILS = "Details of event cannot be blank!";
    public static final String ERROR_FIND_MISSING_KEYWORD = "Keyword was not provided! Please use find <keyword>";
    public static final String ERROR_INVALID_COMMAND = "I am sorry! I don't know what that means!";
    public static final String ERROR_INDEX_OUT_OF_BOUNDS = "Index is out of bounds!";
    public static final String ERROR_NON_INTEGER_INDEX = "Index is not an integer!";
    public static final String ERROR_NO_TASKS_FOUND = "No tasks found with the given keyword";
    public static final String ERROR_TASK_ALREADY_DONE = "Task is already done!";
    public static final String ERROR_ENDTIME_AFTER_STARTTIME = "The end time is after the start time!";

    public static final String ERROR_UNABLE_TO_SAVE_TASK = "Unable to save tasks at the moment";
    public static final String ERROR_UNABLE_TO_FIND_SAVED_TASKS = "Unable to find your saved tasks!";
}
