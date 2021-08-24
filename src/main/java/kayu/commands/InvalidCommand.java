package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_UNKNOWN_COMMAND;
import static kayu.commands.CommandType.INVALID;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * InvalidCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that 
 * is used when the user has inputted an invalid command.
 */
public class InvalidCommand extends Command {
    
    public InvalidCommand() {
        super(INVALID);
    }

    /**
     * See {@link kayu.commands.Command#execute(TaskList)}.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_UNKNOWN_COMMAND);
    }
}
