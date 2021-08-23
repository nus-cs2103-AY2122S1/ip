package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.printTasksWithKeyword(keyword, ui);
    }
}
