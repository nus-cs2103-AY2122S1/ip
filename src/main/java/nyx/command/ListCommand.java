package nyx.command;

import nyx.Storage;
import nyx.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }
}
