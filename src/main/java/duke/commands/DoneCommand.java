package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DoneCommand extends Command{

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        if (this.index < 1 || this.index > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid index number :(\n");
        }

        Task done = taskList.setDone(index);
        ui.doneMessage(done);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
