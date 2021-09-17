package pats.command;

import static java.util.Objects.requireNonNull;

import pats.PatsException;
import pats.Storage;
import pats.TaskList;
import pats.task.Task;
import pats.ui.Ui;

public class DoneCommand extends Command {
    private final int lineIndex;

    public DoneCommand(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    /**
     * Mark a task as done according to line index; outputs the task's information.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     * @throws PatsException if storage fails to write in file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PatsException {
        requireNonNull(ui);
        requireNonNull(storage);

        Task task = taskList.get(lineIndex);
        task.setStatus(true);
        storage.setLine(lineIndex, task.populateSaveData());
        Ui.printMarkDone(task.toString());
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
            return ((DoneCommand) o).lineIndex == this.lineIndex;
        }
        return false;
    }
}
