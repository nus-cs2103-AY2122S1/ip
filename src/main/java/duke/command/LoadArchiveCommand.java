package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.ArchiveException;

public class LoadArchiveCommand extends Command {
    private String fileName;

    public LoadArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws ArchiveException {
        storage.loadArchive(this.fileName, taskList);
        return ui.loadArchive(this.fileName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
