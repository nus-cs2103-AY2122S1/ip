package duke;

public class DukeException extends Exception {

    protected ExceptionType type;

    public DukeException(ExceptionType type) {
        super(errorTypeToMessage(type));
        this.type = type;
    }

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
            return "missing keyword" + Parser.WORD_DEADLINE_BY;
        case EVENT_MISSING_KEYWORD:
            return "missing keyword" + Parser.WORD_EVENT_AT;
        case INVALID_COMMAND:
            return "invalid command";
        case INVALID_OPERAND:
            return "invalid operand";
        case PIPE_SYMBOL:
            return "OOPS!!! Duke cannot identify the symbol \"|\". Please do not include it " +
                    "within your input :)";
        case FAIL_TO_READ:
            return "Failed to read from file";
        case FAIL_TO_WRITE:
            return "Failed to write to file";
        default:
            return "";
        }
    }
}
