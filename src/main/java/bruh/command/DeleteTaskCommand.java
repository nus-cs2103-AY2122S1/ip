package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

public class DeleteTaskCommand extends Command {
    private final int indexToDelete;

    public DeleteTaskCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Got it. I've removed this task:\n  %s\n%s", taskList.remove(indexToDelete).toString(),
                taskList.getTaskCountDesc());
    }
}
