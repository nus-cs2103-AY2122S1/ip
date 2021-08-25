package command;

import duke.TaskList;
import duke.Ui;

public class FindKeywordCommand extends Command {
    private final String keyword;

    public FindKeywordCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.findTasksByKeyword(keyword);
    }
}
