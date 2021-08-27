public class InvalidDateAndTimeException extends DukeException {
    private static final String MISSING_DEADLINE = "missing deadline";
    private static final String MISSING_EVENT_TIME = "missing event time";
    private static final String INVALID_DATE = "invalid date";
    private static final String INVALID_TIME = "invalid time";
    private static final String INVALID_DATE_AND_TIME = "invalid date and time";

    private String errorType;

    public InvalidDateAndTimeException(String errorMessage) {
        super(errorMessage);
        this.errorType = errorMessage;
    }

    @Override
    public String getMessage() {
        switch (errorType) {
        case MISSING_DEADLINE:
            return "☹ OOPS!!! Please provide a deadline with /by.";
        case MISSING_EVENT_TIME:
            return "☹ OOPS!!! Please provide a event time with /at.";
        case INVALID_DATE:
            return "☹ OOPS!!! Please provide a valid date in the format dd/mm/yyyy.";
        case INVALID_TIME:
            return "☹ OOPS!!! Please provide a valid time in the format hh:mm.";
        case INVALID_DATE_AND_TIME:
            return "☹ OOPS!!! Please provide a valid date and time in the format dd/mm/yyyy hh:mm.";
        default:
            return "Error: InvalidDateAndTimeException";
        }
    }
    //todo message for date or time being optional?

}

