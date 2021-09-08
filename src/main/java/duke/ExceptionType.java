package duke;

/**
 * Represents types of {@link duke.DukeException DukeException}.
 */
public enum ExceptionType {
    INDEX_OUT_OF_BOUND, MISSING_OPERAND, DDL_MISSING_KEYWORD, EVENT_MISSING_KEYWORD, INVALID_COMMAND, INVALID_OPERAND,
    PIPE_SYMBOL,
    FAIL_TO_READ, FAIL_TO_WRITE, FAIL_TO_CREATE_FILE,
    OTHERS
}
