package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class FindCommand extends Command {
    private final String find;

    public FindCommand(String find) {
        this.find = find;
    }

    public void execute(TaskList taskList, Storage storage) {
        taskList.find(this.find);
    }

    public boolean getIsExit() {
        return false;
    }
}
