package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** A class for list command. */
public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayList(taskList);
    }
}
