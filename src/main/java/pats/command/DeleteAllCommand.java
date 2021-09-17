package pats.command;

import static java.util.Objects.requireNonNull;

import pats.PatsException;
import pats.Storage;
import pats.TaskList;
import pats.ui.Ui;

public class DeleteAllCommand extends Command {
    /**
     * Deletes all tasks from task list.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     * @throws PatsException if storage fails to write in file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PatsException {
        requireNonNull(taskList);
        requireNonNull(ui);
        requireNonNull(storage);

        taskList.deleteAll();
        storage.removeAll();
        Ui.printRemoveAll();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass();
    }
}
