package kayu.commands;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

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

        return "Commands:\n"
                + "  - bye\n"
                + "  - help \n"
                + "  - list\n"
                + "  - list-notes\n"
                + "  - note [desc]\n"
                + "  - todo [desc]\n"
                + "  - event [desc] /at [date] [time]\n"
                + "  - deadline [desc] /by [date] [time]\n"
                + "  - delete [task-number]\n"
                + "  - delete-note [note-number]\n"
                + "  - done [task-number]\n"
                + "  - find [keywords...]";
    }
}
