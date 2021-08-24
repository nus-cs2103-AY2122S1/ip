package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkTaskDoneCommand extends Command {
    private int indexToMarkDone;

    public MarkTaskDoneCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Nice! I've marked this task as done:\n  %s",
                taskList.markAsDone(indexToMarkDone).toString());
    }
}
