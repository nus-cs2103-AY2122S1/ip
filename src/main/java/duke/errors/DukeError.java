package duke.errors;

public enum DukeError {
    // I prefered to keep the enum structure rather than extend Exceptions as I didn't want to interrupt the UI.
    INVALID_COMMAND(-1, "Unrecognised command detected. Please try again."),
    ESCAPE_CHARACTER(0, "Please do not use the \\n in your input as it makes me sad. ):"),
    POLLUTED_LIST_COMMAND(1, "Please only type 'list' to view the task list."),
    EMPTY_TODO_DESCRIPTION(2, "Empty descriptions for ToDos are not allowed!"),
    EMPTY_DEADLINE_DESCRIPTION(3, "Empty descriptions for Deadlines are not allowed!"),
    EMPTY_DEADLINE_DATE(4, "Your deadline is missing a date."),
    EMPTY_EVENT_DESCRIPTION(5, "Empty descriptions for Events are not allowed!"),
    EMPTY_EVENT_DATE(6, "Your event is missing a date."),
    EMPTY_LIST_NUMBER(7, "Please indicate which item on your list you would like to modify."),
    TOO_MANY_INPUTS(8, "Too many inputs!"),
    INVALID_LIST_NUMBER(9, "Please use a valid list number!"),
    LIST_FULL(10, "The list is full, please remove an existing Task before trying to add a new Task."),
    SEPARATOR_DETECTED(11, "Please do not use '_~_' in your input as it breaks me! ):"),
    INVALID_DATE_FORMAT(12, "Invalid date. Please use the dd-mm-yyyy convention!"),
    POLLUTED_EXIT_COMMAND(13, "Please only type 'bye' to exit");

    private final int code;
    private final String description;

    public static DukeError getError(int code) {
        switch(code) {
            case -1:
                return DukeError.INVALID_COMMAND;
            case 0:
                return DukeError.ESCAPE_CHARACTER;
            case 1:
                return DukeError.POLLUTED_LIST_COMMAND;
            case 2:
                return DukeError.EMPTY_TODO_DESCRIPTION;
            case 3:
                return DukeError.EMPTY_DEADLINE_DESCRIPTION;
            case 4:
                return DukeError.EMPTY_DEADLINE_DATE;
            case 5:
                return DukeError.EMPTY_EVENT_DESCRIPTION;
            case 6:
                return DukeError.EMPTY_EVENT_DATE;
            case 7:
                return DukeError.EMPTY_LIST_NUMBER;
            case 8:
                return DukeError.TOO_MANY_INPUTS;
            case 9:
                return DukeError.INVALID_LIST_NUMBER;
            case 10:
                return DukeError.LIST_FULL;
            case 11:
                return DukeError.SEPARATOR_DETECTED;
            case 12:
                return DukeError.INVALID_DATE_FORMAT;
            case 13:
                return DukeError.POLLUTED_EXIT_COMMAND;
            default:
                return null;
        }
    }

    DukeError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Error " + code + ": " + description;
    }
}
