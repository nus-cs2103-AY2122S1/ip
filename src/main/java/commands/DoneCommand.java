package commands;

import exception.DukeException;
import service.TaskList;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public DoneCommand(String commandParams) {
        super(CommandType.DONE, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.updateTaskAsDone(123);
    }
}
