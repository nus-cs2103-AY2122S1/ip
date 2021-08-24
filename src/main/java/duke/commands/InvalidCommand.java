package duke.commands;

import duke.exception.DukeException;
import duke.service.TaskList;

public class InvalidCommand extends Command {
    
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException(CommandMessage.ERROR_UNKNOWN_COMMAND);
    }
}
