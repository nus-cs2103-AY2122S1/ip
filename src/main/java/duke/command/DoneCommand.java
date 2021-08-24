package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    int markAsDone;

    public DoneCommand(int markAsDone) {
        this.markAsDone = markAsDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTasks().get(markAsDone - 1).markAsDone();
        storage.markAsDone(taskList, markAsDone);
        ui.done(taskList, markAsDone);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
