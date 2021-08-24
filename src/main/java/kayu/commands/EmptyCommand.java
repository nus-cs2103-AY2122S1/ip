package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_COMMAND;
import static kayu.commands.CommandType.EMPTY;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * EmptyCommand class.
 * 
 * This class is an instance of {@link kayu.commands.Command} that 
 * is used when the user has inputted an empty command.
 */
public class EmptyCommand extends Command {

    public EmptyCommand() {
        super(EMPTY);
    }

    /**
     * See {@link kayu.commands.Command#execute(TaskList)}.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_EMPTY_COMMAND);
    }
}
