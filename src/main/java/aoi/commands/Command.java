package aoi.commands;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
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
    public static Command of(Keyword keyword, String[] tokens) throws AoiException {
        Command cmd = null;
        switch(keyword) {
        case ADD:
            cmd = new AddCommand(tokens);
            break;
        case LIST:
            cmd = new ListCommand();
            break;
        case DONE:
            cmd = new DoneCommand(tokens);
            break;
        case DELETE:
            cmd = new DeleteCommand(tokens);
            break;
        case FIND:
            cmd = new FindCommand(tokens);
            break;
        case EXIT:
            cmd = new ExitCommand();
            break;
        default:
            throw new IllegalArgumentException();
        }
        return cmd;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws AoiException;
}
