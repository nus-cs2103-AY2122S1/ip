package duke.command;

import duke.*;

public class ClearCommand extends Command {

    public ClearCommand() {
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        storage.clear();
        taskList.clear();
        return ui.clear();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
