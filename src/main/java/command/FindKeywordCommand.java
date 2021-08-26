package command;

import duke.TaskList;

/**
 * Represents the command that finds tasks on the task list that contains a specific keyword.
 */
public class FindKeywordCommand extends Command {
    private final String keyword;

    public FindKeywordCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.findTasksByKeyword(keyword);
    }
}
