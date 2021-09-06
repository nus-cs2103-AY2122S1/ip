package duke.command;

import duke.*;

public class NewArchiveCommand extends Command {
    private String fileName;

    public NewArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        archive.newArchive(this.fileName);
        return ui.newArchive(this.fileName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
