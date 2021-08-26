package command;

import duke.TaskList;

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
