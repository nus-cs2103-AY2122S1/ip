package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** A class for exit command. */
public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
