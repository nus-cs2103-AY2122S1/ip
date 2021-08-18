public class DukeException extends Exception {
    private final String customMessage;
    
    public DukeException(DukeExceptionType err) {
        switch (err) {
            case DEADLINEDESC:
                this.customMessage = "  OOPS!!! The description of a deadline cannot be empty!";
                break;
            case DEADLINETIME:
                this.customMessage = "  OOPS!!! The time of a deadline cannot be empty!";
                break;
            case EVENTDESC:
                this.customMessage = "  OOPS!!! The description of an event cannot be empty!";
                break;
            case EVENTPERIOD:
                this.customMessage = "  OOPS!!! The period of an event cannot be empty!";
                break;
            case TODODESC:
                this.customMessage = "  OOPS!!! The description of a todo cannot be empty!";
                break;
            case INVALIDDONE:
                this.customMessage = "  OOPS!!! That is not a valid task index for completion!";
                break;
            case INVALIDDELETE:
                this.customMessage = "  OOPS!!! That is not a valid task index for deletion!";
                break;
            case INVALIDINPUT:
                this.customMessage = "  OOPS!!! I don't recognise the command you've given me.";
                break;
            default:
                this.customMessage = "  Duck has run into an unspecified error!";
                break;
        }
    }

    @Override
    public String getMessage() {
        return this.customMessage;
    }
}
