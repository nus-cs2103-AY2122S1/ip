package kayu.commands;

import static kayu.commands.CommandType.HELP;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Represents a {@link kayu.commands.Command} that lists the possible commands a user can key in for operations.
 */
public class HelpCommand extends Command {

    /** Key word for command. */
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
    public String execute(TaskList taskList) throws DukeException {
        return "1. General:\n"
                + "  - bye\n"
                + "  - help \n"
                + "2. Create tasks:\n"
                + "  - todo <desc>\n"
                + "  - event <desc> /at <date> <time>\n"
                + "  - deadline <desc> /by <date> <time>\n"
                + "3. Task actions:\n"
                + "  - list\n"
                + "  - delete <task-number>\n"
                + "  - done <task-number>\n"
                + "  - find <keyword>";
    }
}
