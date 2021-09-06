package duke.command;

import duke.*;

public class ListArchiveCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        return ui.listArchives();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
