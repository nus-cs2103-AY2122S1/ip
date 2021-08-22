package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class InvalidCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Sorry, I don't understand what you are saying!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
