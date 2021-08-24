package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {

    }

    @Override
    public void say(TaskList tasks, Ui ui) {
        ui.sayGoodbye();
    }
}
