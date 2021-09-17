package pats;

/**
 * Signals that an exception has occurred during runtime.
 * Any exception during runtime will be converted to PatsException, resulting an error message to System.out.
 */

public class PatsException extends Exception {

    private final ExceptionType type;

    /**
     * Constructs a PatsException with type of that exception.
     *
     * @param type Type of exception
     */
    public PatsException(ExceptionType type) {
        super(errorTypeToMessage(type));
        this.type = type;
    }

    /**
     * Constructs a PatsException with the type of that exception and additional information describing it.
     *
     * @param type type of exception
     * @param otherMessage additional information describing the exception
     */
    public PatsException(ExceptionType type, String otherMessage) {
        super(errorTypeToMessage(type) + " " + otherMessage);
        this.type = type;
    }

    private static String errorTypeToMessage(ExceptionType type) {
        switch (type) {
        case INDEX_OUT_OF_BOUND:
            return "index is out of range. ";
        case MISSING_OPERAND:
            return "insufficient parameters. ";
        case DDL_MISSING_KEYWORD:
            return "missing keyword " + Parser.WORD_DEADLINE_BY + ". ";
        case EVENT_MISSING_KEYWORD:
            return "missing keyword " + Parser.WORD_EVENT_AT + ". ";
        case INVALID_COMMAND:
            return "invalid command. ";
        case INVALID_OPERAND:
            return "invalid operand. ";
        case PIPE_SYMBOL:
            return "illegal character \"|\". ";
        case HAS_DUPLICATE:
            return "Failed to add task: there is a duplicate task in list that is unfinished. ";
        case FAIL_TO_READ:
            return "Failed to read from file. ";
        case FAIL_TO_WRITE:
            return "Failed to write to file. ";
        case FAIL_TO_CREATE_FILE:
            return "Failed to create file. ";
        case OTHERS:
            return "Error";
        default:
            throw new AssertionError(type);
        }
    }

    public ExceptionType getType() {
        return this.type;
    }
}
