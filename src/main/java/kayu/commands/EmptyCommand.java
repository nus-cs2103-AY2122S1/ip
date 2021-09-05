package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_COMMAND;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;

/**
 * Represents a {@link kayu.commands.Command} that indicates that the user has inputted an empty command.
 */
public class EmptyCommand extends Command {

    /**
     * Initializes an EmptyCommand- {@link kayu.commands.Command}.
     */
    public EmptyCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        throw new KayuException(ERROR_EMPTY_COMMAND);
    }
}
