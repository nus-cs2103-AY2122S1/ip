public class DukeException extends Exception {

    protected enum ErrorType {
        EMPTY_DESCRIPTION,
        INVALID_INPUT;

    }

    protected ErrorType errorType;

    public DukeException(String errorMessage, ErrorType errorType) {
        super(errorMessage);
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        if (errorType.equals(ErrorType.EMPTY_DESCRIPTION)) {
            return "____________________________________________________________\n"
                    + "OOPS!!! The description of a "
                    + this.getMessage()
                    + " cannot be empty.\n"
                    + "____________________________________________________________\n";
        } else if (errorType.equals(ErrorType.INVALID_INPUT)) {
            return "____________________________________________________________\n"
                    + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "____________________________________________________________\n";
        } else {
            return "Unknown Error";
        }
    }
}
