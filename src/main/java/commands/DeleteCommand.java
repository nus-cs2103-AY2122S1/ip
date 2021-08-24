package commands;

import exception.DukeException;
import service.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String commandParams) {
        super(CommandType.DELETE, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.deleteTask(123);
    }
}
