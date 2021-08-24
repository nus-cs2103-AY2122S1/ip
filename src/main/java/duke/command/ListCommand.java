package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }
}
