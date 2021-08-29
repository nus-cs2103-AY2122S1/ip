package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return responseLogic.goodByeResponse();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
