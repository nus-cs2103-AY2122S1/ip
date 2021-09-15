package duke.commands;

import duke.exceptions.AuguryException;
import duke.exceptions.UnknownCommandException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * {@code Command} represents a command to be run by Augury.
 */
public abstract class Command {

    /**
     * Static factory method to create {@code Command}s.
     *
     * @param commandType enum representing type of {@code Command} to be created.
     * @param arguments optional parameter of user-supplied arguments.
     * @return {@code Command} object.
     * @throws UnknownCommandException if {@code Command} is not one of the accepted commands
     */
    public static Command of (CommandTypes commandType, String... arguments) throws UnknownCommandException {
        switch(commandType) {
        case QUIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case HELP:
            return new HelpCommand();
        case SORT:
            return new SortCommand(arguments);
        case MARKDONE:
            return new MarkDoneCommand(arguments);
        case DELETE:
            return new DeleteCommand(arguments);
        case FIND:
            return new FindCommand(arguments);
        case MAKE:
            return new MakeCommand(arguments);
        default:
            throw new UnknownCommandException("Unknown command.");
        }
    }

    /**
     * Executes the {@code Command}, and returns a String which contains
     * information of the execution.
     *
     * @param tasks {@code TaskList} that the Command operates on
     * @param storage {@code Storage} instance that the Command operates on
     * @return {@code String} containing information of execution.
     * @throws AuguryException if invalid operation occurs during execution.
     */
    public abstract String execute (TaskList tasks, Storage storage) throws AuguryException;

}
