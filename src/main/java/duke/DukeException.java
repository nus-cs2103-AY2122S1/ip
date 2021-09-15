package duke;

/**
 * Signals that an exception has occurred during runtime.
 * Any exception during runtime will be converted to DukeException, resulting an error message to System.out.
 */

public class DukeException extends Exception {

    private final ExceptionType type;

    /**
     * Constructs a DukeException with type of that exception.
     * @param type Type of exception
     */
    public DukeException(ExceptionType type) {
        super(errorTypeToMessage(type));
        this.type = type;
    }

    /**
     * Constructs a DukeException with the type of that exception and additional information describing it.
     * @param type type of exception
     * @param otherMessage additional information describing the exception
     */
    public DukeException(ExceptionType type, String otherMessage) {
        super(errorTypeToMessage(type) + " " + otherMessage);
        this.type = type;
    }

    private static String errorTypeToMessage(ExceptionType type) {
        switch (type) {
        case INDEX_OUT_OF_BOUND:
            return "index out of range";
        case MISSING_OPERAND:
            return "missing operand";
        case DDL_MISSING_KEYWORD:
            return "missing keyword " + Parser.WORD_DEADLINE_BY;
        case EVENT_MISSING_KEYWORD:
            return "missing keyword " + Parser.WORD_EVENT_AT;
        case INVALID_COMMAND:
            return "invalid command";
        case INVALID_OPERAND:
            return "invalid operand";
        case PIPE_SYMBOL:
            return "OOPS!!! Duke cannot identify the symbol \"|\". Please do not include it "
                    + "within your input :)";
        case HAS_DUPLICATE:
            return "Failed to add task: there is a duplicate task in list that is unfinished.";
        case FAIL_TO_READ:
            return "Failed to read from file";
        case FAIL_TO_WRITE:
            return "Failed to write to file";
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
