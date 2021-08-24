package commands;

import exception.DukeException;
import service.TaskList;
import task.Task;
import task.Todo;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String commandParams) {
        super(CommandType.TODO, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "";
    }
}
