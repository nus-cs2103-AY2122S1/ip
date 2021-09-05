package kayu.commands;

import static kayu.commands.CommandType.BYE;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;

/**
 * Represents a {@link kayu.commands.Command} that indicates the termination of the program.
 */
public class ByeCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "bye";

    /**
     * Initializes a Bye- {@link kayu.commands.Command}.
     */
    public ByeCommand() {
        super(BYE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        return CommandMessage.MESSAGE_BYE;
    }
}
