package Duke.DukeException;

public abstract class DukeException extends RuntimeException {

    /**
     * INCOMPLETE: Command is incomplete
     * OUT_OF_BOUND: The index number is not within task list size
     * SYNTAX_ERROR: Command type is not recognized
     * INVALID_FORMAT: Date and time format entered are not supported
     */
    public enum Type {INCOMPLETE, OUT_OF_BOUND, SYNTAX_ERROR, INVALID_FORMAT, WRONG_COMMAND}

    private final String DEFAULT_STRING = "Please type in 'help' for instructions";

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
        return super.getMessage() + DEFAULT_STRING;
    }
}

