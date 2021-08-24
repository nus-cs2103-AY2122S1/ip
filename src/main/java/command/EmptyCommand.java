package command;

import exception.DukeException;
import service.TaskList;

public class EmptyCommand extends Command {

    public EmptyCommand() {
        super(CommandType.EMPTY);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(CommandMessage.ERROR_EMPTY_COMMAND);
    }
}
