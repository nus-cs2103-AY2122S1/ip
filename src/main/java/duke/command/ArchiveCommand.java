package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ArchiveCommand extends Command {
    private final int indexToArchive;

    public ArchiveCommand(int indexToRemove) {
        this.indexToArchive = indexToRemove;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        if (indexToArchive == -1) {

        } else {
            Task toArchive = taskList.remove(indexToArchive);

            archiveList.add(toArchive);

            String message = "Noted. I've archived this task:\n" + toArchive + "\nNow you have "
                    + taskList.getSize() + " tasks in the list";

            ui.print(message);
        }
    }

    @Override
    public String getExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        if (indexToArchive == -1) {
            return "archive all";
        } else {
            Task toArchive = taskList.remove(indexToArchive);

            archiveList.add(toArchive);

            String message = "Noted. I've archived this task:\n" + toArchive + "\nNow you have "
                    + taskList.getSize() + " tasks in the list";

            return message;
        }
    }
}
