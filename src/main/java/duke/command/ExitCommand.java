package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class ExitCommand implements Commandable {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! See you next time!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
