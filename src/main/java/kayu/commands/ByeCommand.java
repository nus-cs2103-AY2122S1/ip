package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return ""; // fall through
    }
}
