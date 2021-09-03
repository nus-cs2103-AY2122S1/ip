package lifeline.util;

public class ErrorString {
    public final static String ERROR_DEADLINE_INCORRECT_FORMAT = "Deadline is not of the correct format! Please use "
            + "deadline <name> /by <dd/MM/yy HHmm>";
    public final static String ERROR_EVENT_INCORRECT_FORMAT = "Event date/time not in proper format! Please use event"
            + " <name> /at <dd/MM/yy> <HHmm>-<HHmm>";
    public final static String ERROR_DONE_MISSING_INDEX = "You did not specify an integer! Please use done <number>";
    public final static String ERROR_DELETE_MISSING_INDEX = "You did not specify an integer! Please use delete "
            + "<number>";
    public final static String ERROR_TODO_MISSING_DETAILS = "Details of todo cannot be blank!";
    public final static String ERROR_DEADLINE_MISSING_DETAILS = "Details of deadline cannot be blank!";
    public final static String ERROR_EVENT_MISSING_DETAILS = "Details of event cannot be blank!";
    public final static String ERROR_FIND_MISSING_KEYWORD = "Keyword was not provided! Please use find <keyword>";
    public final static String ERROR_INVALID_COMMAND = "I am sorry! I don't know what that means!";
    public final static String ERROR_INDEX_OUT_OF_BOUNDS = "Index is out of bounds!";
    public final static String ERROR_NON_INTEGER_INDEX = "Index is not an integer!";
}
