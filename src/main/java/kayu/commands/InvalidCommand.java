package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_UNKNOWN_COMMAND;
import static kayu.commands.CommandType.INVALID;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Represents a {@link kayu.commands.Command} that indicates that the user has inputted an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Initializes a InvalidCommand- {@link kayu.commands.Command}.
     */
    public InvalidCommand() {
        super(INVALID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_UNKNOWN_COMMAND);
    }
}
