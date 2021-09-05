package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for saying goodbye. */
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
