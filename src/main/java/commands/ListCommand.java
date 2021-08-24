package commands;

import exception.DukeException;
import service.TaskList;

public class ListCommand extends Command {
    
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super(CommandType.LIST);
    }
    
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.getTaskList();
    }
}
