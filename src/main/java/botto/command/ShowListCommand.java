package botto.command;

import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public class ShowListCommand implements Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList.getTasks(), "Here are the tasks in your list:");
    }
}
