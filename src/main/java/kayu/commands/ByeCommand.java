package kayu.commands;

import static kayu.commands.CommandType.BYE;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * ByeCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that uses 
 * the keyword {@link #COMMAND_WORD}. It indicates the termination of the program.
 */
public class ByeCommand extends Command {

    /** Key word for command. */
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
    public String execute(TaskList taskList) throws DukeException {
        return ""; // fall through
    }
}
