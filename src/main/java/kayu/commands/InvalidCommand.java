package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_UNKNOWN_COMMAND;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

/**
 * Represents a {@link kayu.commands.Command} that indicates that the user has inputted an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Initializes an InvalidCommand- {@link kayu.commands.Command}.
     */
    public InvalidCommand() {
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

        throw new KayuException(ERROR_UNKNOWN_COMMAND);
    }
}
