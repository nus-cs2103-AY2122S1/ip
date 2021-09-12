package duck.exception;

/**
 * Handles error messages whenever the Duck program throws an exception.
 */
public class DuckException extends Exception {
    private final String customMessage;

    /**
     * Constructor for a DuckException object.
     *
     * @param err The type of DuckException thrown.
     */
    public DuckException(DuckExceptionType err) {
        switch (err) {
        case CLOSE_INTERRUPT:
            customMessage = "QUACK!!! There was a problem closing the program!";
            break;
        case DB_ADD:
            customMessage = "QUACK!!! There was a problem adding the task to the hard disk!";
            break;
        case DB_DELETE:
            customMessage = "QUACK!!! There was a problem deleting the task from the hard disk!";
            break;
        case DB_DONE:
            customMessage = "QUACK!!! There was a problem setting the task to done in the hard disk!";
            break;
        case DB_LAUNCH:
            customMessage = "QUACK!!! There was a problem setting up the hard disk!";
            break;
        case DB_READ:
            customMessage = "QUACK!!! There was a problem with reading tasks in the hard disk!\n"
                    + "Check out duke.txt for any erroneous entries or incorrect formatting.";
            break;
        case INVALID_DATETIME:
            customMessage = "QUACK!!! The date you provided is not in a correct format!\n"
                    + "The required format is yyyy-mm-dd or yyyy-mm-dd hh:mm.";
            break;
        case INVALID_FIND:
            customMessage = "QUACK!!! You need to specify a keyword!";
            break;
        case INVALID_INPUT:
            customMessage = "QUACK!!! I don't recognise the command you've given me.";
            break;
        case INVALID_PERIOD:
            customMessage = "QUACK!!! The event period you provided is not in a correct format!\n"
                    + "  You can provide:\n"
                    + "    1. start and end dates\n"
                    + "    2. one date, start and end times\n"
                    + "    3. start date and time, end date and time";
            break;
        case INVALID_SCHEDULE:
            customMessage = "QUACK!!! You need to specify a date!";
            break;
        case INVALID_TASK_INDEX:
            customMessage = "QUACK!!! That is not a valid task index!";
            break;
        case MISSING_DEADLINE_DATETIME:
            customMessage = "QUACK!!! The time of a deadline cannot be empty!\n"
                    + "Remember to use the /by keyword.";
            break;
        case MISSING_DEADLINE_DESC:
            customMessage = "QUACK!!! The description of a deadline cannot be empty!";
            break;
        case MISSING_EVENT_DESC:
            customMessage = "QUACK!!! The description of an event cannot be empty!";
            break;
        case MISSING_EVENT_PERIOD:
            customMessage = "QUACK!!! The period of an event cannot be empty!\n"
                    + "Remember to use the /at keyword.";
            break;
        case MISSING_TODO_DESC:
            customMessage = "QUACK!!! The description of a todo cannot be empty!";
            break;
        default:
            customMessage = "QUACK!!! Duck has run into an unspecified error!";
            break;
        }
    }

    /**
     * Returns a string representation of the DuckException.
     *
     * @return a string representing the exception.
     */
    @Override
    public String getMessage() {
        return customMessage;
    }
}
