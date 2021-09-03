package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.findAndListTasks(keyword);
        return "";
    }

    public boolean shouldExit() {
        return false;
    }
}
