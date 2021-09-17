package hyddd.exceptions;

/**
 * @@author Hang Zelin
 *
 * Enum that stores all types of possible exceptions.
 */
public enum ExceptionType {
    UNKNOWN_OPERATION, NO_TASK_ERROR, WRONG_INDEX_ERROR, DEADLINE_FORMAT_ERROR, EVENT_FORMAT_ERROR,
    TELL_FORMAT_ERROR, FILE_WRITE_ERROR, FILE_READ_ERROR, EMPTY_COMMAND_ERROR, DUPLICATE_TASK_ERROR;
}
