package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_COMMAND;
import static kayu.commands.CommandType.EMPTY;

import kayu.exception.DukeException;
import kayu.service.TaskList;

public class EmptyCommand extends Command {

    public EmptyCommand() {
        super(EMPTY);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(ERROR_EMPTY_COMMAND);
    }
}
