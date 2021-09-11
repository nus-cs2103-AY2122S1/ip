package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Have a pleasant day! See you next time!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
