package commands;

import exception.DukeException;
import service.TaskList;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String commandParams) {
        super(CommandType.DEADLINE, commandParams);
    }
    
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "";
    }

}

