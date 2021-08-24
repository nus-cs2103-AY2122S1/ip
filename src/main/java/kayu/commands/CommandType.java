package kayu.commands;

/**
 * Command enum class.
 *
 * This enum class is used by {@link kayu.commands.Command} and 
 * helps direct the {@link kayu.service.TaskList} to the right operations. 
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
