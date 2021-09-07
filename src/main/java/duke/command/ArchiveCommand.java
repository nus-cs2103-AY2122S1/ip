package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ArchiveCommand extends Command {
    private final int indexToArchive;
    private final boolean isArchiveAll;

    public ArchiveCommand(int indexToRemove) {
        this.indexToArchive = indexToRemove;
        this.isArchiveAll = indexToArchive == -1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isArchiveAll) {
            message = formatAndArchiveAll(taskList, archiveList);

        } else {
            message = formatAndArchiveIndex(taskList, archiveList);

        }
        ui.print(message);
    }

    @Override
    public String getExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isArchiveAll) {
            message = formatAndArchiveAll(taskList, archiveList);

        } else {
            message = formatAndArchiveIndex(taskList, archiveList);

        }
        return message;
    }

    private String formatAndArchiveIndex(TaskList taskList, ArchiveList archiveList) {
        Task toArchive = taskList.archive(indexToArchive, archiveList);

        return "Noted. I've archived this task:\n" + toArchive + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }

    private String formatAndArchiveAll(TaskList taskList, ArchiveList archiveList) {
        String message = "Noted. I've archived these tasks:\n";

        int counter = 0;
        int size = taskList.getSize();

        for (int i = 0; i < size; i++) {
            Task toArchive = taskList.archive(0, archiveList);
            counter++;
            message += counter + "." + toArchive + "\n";
        }

        message += "Now you have 0 tasks in the list";
        return message;
    }
}
