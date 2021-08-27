package kayu.commands;

/**
 * Helps {@link kayu.commands.Command} by directing the {@link kayu.service.TaskList} to the right operations.
 */
public enum CommandType {
    BYE,
    DEADLINE,
    DELETE,
    DONE,
    EMPTY,
    EVENT,
    FIND,
    HELP,
    INVALID,
    LIST,
    TODO;
}
