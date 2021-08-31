package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class FindTasksCommand extends Command {
    String keyword;
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksWithKeyword = tasks.findTasksWithKeyword(keyword);
        tasksWithKeyword.printTaskList(true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindTasksCommand) {
            FindTasksCommand otherCommand = (FindTasksCommand) obj;
            return keyword.equals(otherCommand.keyword);
        }
        return false;
    }
}
