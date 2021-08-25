package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

public class MarkDoneCommand extends Command {
    private final int index;
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        tasks.markDone(this.index);
        ui.showMarkDone(tasks.getTask(this.index));
        fileManager.updateTaskList(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
