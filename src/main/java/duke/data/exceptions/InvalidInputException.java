package duke.data.exceptions;

public class InvalidInputException extends DukeException {
    private String errorMessage;

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        if (errorMessage.equals("invalid task number entered")) {
            return "☹ OOPS!!! Please key in a valid task number within the list.";
        } else if (errorMessage.equals("error")) {
            return "☹ OOPS!!! Error occurred.";
        } else if (errorMessage.equals("invalid find")) {
            return "☹ OOPS!!! Please key in a valid search term.";
        } else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
