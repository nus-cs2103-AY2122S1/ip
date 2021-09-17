package aoi.commands;

import aoi.data.TaskList;
import aoi.storage.Storage;

/**
 * Encapsulates the commands to be executed by Aoi Todo Bot.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public abstract class Command {
    /**
     * Factory method of Command.
     *
     * @param keyword Keyword to denote which command to generate.
     * @return Command associated to the keyword.
     * @throws IllegalArgumentException if keyword is not supported.
     */
    public static Command of(Keyword keyword, String[] tokens) throws IllegalArgumentException {
        Command cmd = null;
        switch(keyword) {
        case ADD:
            cmd = new AddCommand(tokens);
            break;
        case LIST:
            cmd = new ListCommand();
            break;
        case DONE:
            cmd = new DoneCommand();
            break;
        case DELETE:
            cmd = new DeleteCommand();
            break;
        case FIND:
            cmd = new FindCommand();
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tasks, Storage storage);
}
