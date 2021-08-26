package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

public class MarkTaskDoneCommand extends Command {
    private final int indexToMarkDone;

    public MarkTaskDoneCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Nice! I've marked this task as done:\n  %s",
                taskList.markAsDone(indexToMarkDone).toString());
    }
}
