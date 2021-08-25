package Duke.DukeException;

public abstract class DukeException extends RuntimeException {

    /**
     * INCOMPLETE: Command is incomplete
     * OUT_OF_BOUND: The index number is not within task list size
     * SYNTAX_ERROR: Command type is not recognized
     * INVALID_FORMAT: Date and time format entered are not supported
     */
    public enum Type {INCOMPLETE, OUT_OF_BOUND, SYNTAX_ERROR, INVALID_FORMAT}


    protected final Type type;

    /**
     * Constructor for Duke Exception class
     * @param message Error message
     * @param type Type of Duke Exception indicated
     */
    public DukeException(String message, Type type) {
        super(message);
        this.type = type;
    }

    /**
     * Indicating the message error for each type of Duke Exception used
     * @return message error of Duke Exception
     */
    @Override
    public String getMessage() {
        switch (type) {
        case INCOMPLETE:
            return "☹ OOPS!!! Your command is incomplete!\n" +
                    "Please enter 'help' for further instructions!";
        case OUT_OF_BOUND:
            return "☹ OOPS!!! Your task number is out of bound!\n" +
                    "Please enter 'help' for further instructions!";
        case SYNTAX_ERROR:
            return "☹ OOPS! I'm sorry, but I don't know what that means :-(\n" +
                    "Please enter 'help' for further instructions!";
        case INVALID_FORMAT:
            return "OOPS! Please enter the date in the following format:\n" +
                    "YYYY-MM-DD (optionally, HH:MM)";
        default:
            return super.getMessage();
        }
    }
}

