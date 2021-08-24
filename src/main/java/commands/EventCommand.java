package commands;

import exception.DukeException;
import service.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public EventCommand(String commandParams) {
        super(CommandType.EVENT, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "";
    }
}
