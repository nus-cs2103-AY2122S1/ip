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
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand && ((FindCommand) obj).keyword.equals(this.keyword);
    }
}
