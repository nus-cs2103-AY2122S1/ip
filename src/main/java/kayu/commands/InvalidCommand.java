package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_UNKNOWN_COMMAND;
import static kayu.commands.CommandType.INVALID;

import kayu.exception.DukeException;
import kayu.service.TaskList;

public class InvalidCommand extends Command {
    
    public InvalidCommand() {
        super(INVALID);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_UNKNOWN_COMMAND);
    }
}
