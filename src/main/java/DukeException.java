public class DukeException extends Exception {
    private final String customMessage;

    public DukeException(DukeExceptionType err) {
        switch (err) {
        case DEADLINEDESC:
            customMessage = "  OOPS!!! The description of a deadline cannot be empty!";
            break;
        case DEADLINETIME:
            customMessage = "  OOPS!!! The time of a deadline cannot be empty!";
            break;
        case EVENTDESC:
            customMessage = "  OOPS!!! The description of an event cannot be empty!";
            break;
        case EVENTPERIOD:
            customMessage = "  OOPS!!! The period of an event cannot be empty!";
            break;
        case TODODESC:
            customMessage = "  OOPS!!! The description of a todo cannot be empty!";
            break;
        case INVALIDDONE:
            customMessage = "  OOPS!!! That is not a valid task index for completion!";
            break;
        case INVALIDDELETE:
            customMessage = "  OOPS!!! That is not a valid task index for deletion!";
            break;
        case INVALIDINPUT:
            customMessage = "  OOPS!!! I don't recognise the command you've given me.";
            break;
        case INVALIDDATE:
            customMessage = "  OOPS!!! The deadline you provided is not in a correct format!\n"
                    + "  Some formats you can use are yyyy/mm/dd, yyyy-mm-dd etc.";
            break;
        default:
            customMessage = "  OOPS!!! Duck has run into an unspecified error!";
            break;
        }
    }

    @Override
    public String getMessage() {
        return customMessage;
    }
}
