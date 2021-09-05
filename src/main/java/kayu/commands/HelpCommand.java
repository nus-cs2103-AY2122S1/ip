package kayu.commands;

import static kayu.commands.CommandType.HELP;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;

/**
 * Represents a {@link kayu.commands.Command} that lists the possible commands a user can key in for operations.
 */
public class HelpCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "help";

    /**
     * Initializes a Help- {@link kayu.commands.Command}.
     */
    public HelpCommand() {
        super(HELP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        return "Commands:\n"
                + "  - bye\n"
                + "  - help \n"
                + "  - list\n"
                + "  - todo [desc]\n"
                + "  - event [desc] /at [date] [time]\n"
                + "  - deadline [desc] /by [date] [time]\n"
                + "  - delete [task-number]\n"
                + "  - done [task-number]\n"
                + "  - find [keywords...]";
    }
}
