package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListArchiveCommand extends ListCommand {
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        ui.print(archiveList);
    }

    @Override
    public String getExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        return ui.getPrintString(archiveList);
    }
}
