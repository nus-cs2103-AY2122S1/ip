package duke.command;

import duke.Archive;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
