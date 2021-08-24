package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printList(taskList.getTaskList());
    }

    @Override
    public boolean getIsExit() {
        return false;
    }
}
