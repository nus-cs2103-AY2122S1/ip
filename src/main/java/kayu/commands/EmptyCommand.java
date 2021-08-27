package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_COMMAND;
import static kayu.commands.CommandType.EMPTY;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Represents a {@link kayu.commands.Command} that indicates that the user has inputted an empty command.
 */
public class EmptyCommand extends Command {

    /**
     * Initializes a EmptyCommand- {@link kayu.commands.Command}.
     */
    public EmptyCommand() {
        super(EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_EMPTY_COMMAND);
    }
}
