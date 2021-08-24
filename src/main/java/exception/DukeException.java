package exception;

public class DukeException extends Exception {
    private final String customMessage;

    public DukeException(DukeExceptionType err) {
        switch (err) {
        case DB_ADD:
            customMessage = "  OOPS!!! There was a problem adding the task to the hard disk!";
            break;
        case DB_DELETE:
            customMessage = "  OOPS!!! There was a problem deleting the task from the hard disk!";
            break;
        case DB_DONE:
            customMessage = "  OOPS!!! There was a problem setting the task to done in the hard disk!";
            break;
        case DB_LAUNCH:
            customMessage = "  OOPS!!! There was a problem setting up the hard disk!";
            break;
        case DB_READ:
            customMessage = "  OOPS!!! There was a problem with reading tasks in the hard disk!\n"
                    + "  Check out duke.txt for any erroneous entries or incorrect formatting.";
            break;
        case INVALID_DATETIME:
            customMessage = "  OOPS!!! The deadline you provided is not in a correct format!\n"
                    + "  The required format is yyyy-mm-dd or yyyy-mm-dd hh:mm.";
            break;
        case INVALID_FIND:
            customMessage = "  OOPS!!! You need to specify a date!";
            break;
        case INVALID_INPUT:
            customMessage = "  OOPS!!! I don't recognise the command you've given me.";
            break;
        case INVALID_PERIOD:
            customMessage = "  OOPS!!! The event period you provided is not in a correct format!\n"
                    + "  You can provide:\n"
                    + "    1. start and end dates\n"
                    + "    2. one date, start and end times\n"
                    + "    3. start date and time, end date and time";
            break;
        case INVALID_TASK_INDEX:
            customMessage = "  OOPS!!! That is not a valid task index!";
            break;
        case MISSING_DEADLINE_DATETIME:
            customMessage = "  OOPS!!! The time of a deadline cannot be empty!";
            break;
        case MISSING_DEADLINE_DESC:
            customMessage = "  OOPS!!! The description of a deadline cannot be empty!";
            break;
        case MISSING_EVENT_DESC:
            customMessage = "  OOPS!!! The description of an event cannot be empty!";
            break;
        case MISSING_EVENT_PERIOD:
            customMessage = "  OOPS!!! The period of an event cannot be empty!";
            break;
        case MISSING_TODO_DESC:
            customMessage = "  OOPS!!! The description of a todo cannot be empty!";
            break;
        default:
            customMessage = "  OOPS!!! Duke has run into an unspecified error!";
            break;
        }
    }

    @Override
    public String getMessage() {
        return customMessage;
    }
}
