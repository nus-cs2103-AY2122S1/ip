package duke.command;

import duke.*;

public class LoadArchiveCommand extends Command {
    private String fileName;

    public LoadArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        storage.loadArchive(this.fileName, taskList);
        return ui.loadArchive(this.fileName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
