package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = tasks.find(keyword);
        return ui.listAllTasks(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            FindCommand temp = (FindCommand) obj;
            return this.keyword.equals(temp.keyword);
        } else {
            return false;
        }
    }
}
