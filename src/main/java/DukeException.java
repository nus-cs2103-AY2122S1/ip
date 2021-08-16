public class DukeException extends Exception {
    private final String customMessage;
    
    public DukeException(String err) {
        switch (err) {
            case "deadlineDesc":
                this.customMessage = "  OOPS!!! The description of a deadline cannot be empty!";
                break;
            case "deadlineTime":
                this.customMessage = "  OOPS!!! The time of a deadline cannot be empty!";
                break;
            case "eventDesc":
                this.customMessage = "  OOPS!!! The description of an event cannot be empty!";
                break;
            case "eventPeriod":
                this.customMessage = "  OOPS!!! The period of an event cannot be empty!";
                break;
            case "todoDesc":
                this.customMessage = "  OOPS!!! The description of a todo cannot be empty!";
                break;
            case "invalidDone":
                this.customMessage = "  OOPS!!! That is not a valid task index!";
                break;
            case "invalidInput":
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
