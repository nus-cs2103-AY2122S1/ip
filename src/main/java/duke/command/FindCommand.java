package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

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
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = fullCommand.substring(5, fullCommand.length() - 1);
        return taskList.showFind(keyword);
    }
}
