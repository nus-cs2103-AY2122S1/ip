public class DukeException extends Exception {

    protected ExceptionType type;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.type = ExceptionType.OTHERS;
    }

    public DukeException(ExceptionType type) {
        super(errorTypeToMessage(type));
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
            default:
                return "";
        }
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
