package kayu.commands;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

/**
 * Represents a {@link kayu.commands.Command} that indicates the termination of the program.
 */
public class ByeCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "bye";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBye() {
        return true;
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

        return CommandMessage.MESSAGE_BYE;
    }
}
