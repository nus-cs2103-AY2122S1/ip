package kayu.commands;

import static kayu.commands.CommandType.BYE;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Represents a {@link kayu.commands.Command} that indicates the termination of the program.
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
