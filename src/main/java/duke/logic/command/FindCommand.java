package duke.logic.command;

import duke.logic.tasks.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.findTask(keyword);
    }
}
