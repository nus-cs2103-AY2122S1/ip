package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        if (this.index < 1 || this.index > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid index number :(\n");
        }

        Task deleted = taskList.delete(index);
        ui.deleteMessage(taskList, deleted);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
