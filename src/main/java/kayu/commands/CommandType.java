package kayu.commands;

/**
 * Command enum class.
 *
 * This enum class is used by the Parser and helps direct the TaskList
 * to the right operations. They follow the user inputs used to trigger such Duke.commands.
 */
public enum CommandType {
    BYE,
    LIST,
    DONE,
    DELETE,
    FIND,
    TODO,
    EVENT,
    DEADLINE,
    INVALID,
    EMPTY;
}
