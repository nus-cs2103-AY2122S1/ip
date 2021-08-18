package utils;

/**
 * Command enum class.
 *
 * This enum class is used by the service.Parser and helps direct the service.TaskManager to the right operations.
 * They follow the user inputs used to trigger such commands.
 */
public enum Command {
    BYE,
    LIST,
    DONE,
    DELETE,
    TODO,
    EVENT,
    DEADLINE,
    UNKNOWN,
    EMPTY;
}
