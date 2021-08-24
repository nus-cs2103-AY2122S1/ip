package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

public class FindCommand extends Command{
    private final String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = fullCommand.substring(5, fullCommand.length() - 1);
        taskList.showFind(keyword);
    }
}
