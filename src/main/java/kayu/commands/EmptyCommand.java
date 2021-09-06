package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_COMMAND;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

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
    public String execute(TaskList taskList,
                          TaskStorage taskStorage,
                          NoteList noteList,
                          NoteStorage noteStorage)
            throws KayuException, StorageException {

        throw new KayuException(ERROR_EMPTY_COMMAND);
    }
}
