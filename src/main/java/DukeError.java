public enum DukeError {
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
    INVALID_DATE_FORMAT(12, "Invalid date. Please use the dd-mm-yyyy convention!");



    private final int code;
    private final String description;

    private DukeError(int code, String description) {
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
        return code + ": " + description;
    }
}
