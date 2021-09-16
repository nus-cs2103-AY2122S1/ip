package duke.command;

import static java.util.Objects.requireNonNull;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int lineIndex;

    public DeleteCommand(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    /**
     * Remove a task from task list according to line index; outputs the deleted task's information.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     * @throws DukeException if storage fails to write in file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        requireNonNull(taskList);
        requireNonNull(ui);
        requireNonNull(storage);

        storage.removeLine(lineIndex);
        Ui.printRemoveTask(taskList.get(lineIndex).toString());
        taskList.remove(lineIndex);
        Ui.printTaskCount(taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((DeleteCommand) o).lineIndex == this.lineIndex;
        }
        return false;
    }
}
