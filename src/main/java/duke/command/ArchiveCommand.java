package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class ArchiveCommand extends Command{
    private final String fullCommand;
    private final boolean isArchive;

    public ArchiveCommand(String fullCommand, boolean isArchive) {
        this.fullCommand = fullCommand;
        this.isArchive = isArchive;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        int archId = Integer.parseInt(fullCommand.split(" ")[1]);
        return (isArchive)
                ? taskList.archiveTask(archId)
                : taskList.unarchiveTask(archId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArchiveCommand that = (ArchiveCommand) o;

        if (isArchive != that.isArchive) return false;
        return fullCommand != null ? fullCommand.equals(that.fullCommand) : that.fullCommand == null;
    }
}
