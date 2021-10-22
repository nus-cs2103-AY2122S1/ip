package captain.command;

import captain.Storage;
import captain.Ui;
import captain.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getListSize() == 0 ? ui.showEmptyTaskList() : ui.showTaskList(tasks);
    }
}
