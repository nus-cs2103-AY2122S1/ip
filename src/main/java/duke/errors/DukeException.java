package duke.errors;

/**
 * The enumeration of the known errors produced by Duke. I opted to use enums instead of
 * making a class that extends the Exceptions class as it is cleaner to flag since the CLI
 * is the mode of interaction with the user.
 */
public class DukeException extends Exception {

    public enum DukeExceptionType {

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
        LIST_FULL(10, "The list is full, "
                + "please remove an existing Task before trying to add a new Task."),
        SEPARATOR_DETECTED(11, "Please do not use '_~_' in your input as it breaks me! ):"),
        INVALID_DATE_FORMAT(12, "Invalid date. Please use the dd-mm-yyyy convention!"),
        POLLUTED_EXIT_COMMAND(13, "Please only type 'bye' to exit"),
        EMPTY_SEARCH_STRING(14, "Please key in something for me to find!");

        /** The error code. */
        private final int code;

        /** The error message. */
        private final String description;

        DukeExceptionType (int code, String description) {
            this.code = code;
            this.description = description;
        }
        /**
         * This method gets the respective DukeError from the code.
         *
         * @param code The error code
         * @return The respective DukeError
         */
        public static DukeExceptionType getError(int code) {
            switch (code) {
                case -1:
                    return INVALID_COMMAND;
                case 0:
                    return ESCAPE_CHARACTER;
                case 1:
                    return POLLUTED_LIST_COMMAND;
                case 2:
                    return EMPTY_TODO_DESCRIPTION;
                case 3:
                    return EMPTY_DEADLINE_DESCRIPTION;
                case 4:
                    return EMPTY_DEADLINE_DATE;
                case 5:
                    return EMPTY_EVENT_DESCRIPTION;
                case 6:
                    return EMPTY_EVENT_DATE;
                case 7:
                    return EMPTY_LIST_NUMBER;
                case 8:
                    return TOO_MANY_INPUTS;
                case 9:
                    return INVALID_LIST_NUMBER;
                case 10:
                    return LIST_FULL;
                case 11:
                    return SEPARATOR_DETECTED;
                case 12:
                    return INVALID_DATE_FORMAT;
                case 13:
                    return POLLUTED_EXIT_COMMAND;
                case 14:
                    return EMPTY_SEARCH_STRING;
                default:
                    return null;
            }
        }
    }

    /** The error code. */
    private final DukeExceptionType errorType;

    /**
     * The constructor for the DukeException.
     *
     * @param code The error code
     */
    public DukeException (int code) {
        this.errorType = DukeExceptionType.getError(code);
    }






    /**
     * Gets the description of the DukeError object.
     *
     * @return The error message
     */
    public String getDescription() {
        return this.errorType.description;
    }

    /**
     * Gets the error message of the DukeError object.
     *
     * @return The error code
     */
    public int getCode() {
        return this.errorType.code;
    }

    @Override
    public String toString() {
        return "DukeError " + this.getCode() + ": " + this.getDescription();
    }
}
