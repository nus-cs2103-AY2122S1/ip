package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTasksWithKeyword(keyword, responseLogic);
    }
}
