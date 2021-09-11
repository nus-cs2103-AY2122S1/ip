package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.ArchiveException;

public class DeleteArchiveCommand extends Command {
    private String archiveName;

    public DeleteArchiveCommand(String archiveName) {
        this.archiveName = archiveName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws ArchiveException {
        archive.deleteArchive(archiveName);
        return ui.deleteArchive(this.archiveName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
